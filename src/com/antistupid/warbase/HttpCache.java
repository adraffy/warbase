package com.antistupid.warbase;

import com.antistupid.warbase.utils.SHA1;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Arrays;
import com.antistupid.warbase.utils.Misc;

public class HttpCache {

    @FunctionalInterface
    static public interface Callback {
        void gotData(Object key, String error, byte[] data);
    }
    
    static class R {
        final Object key;
        final String url;
        final Callback cb;
        final boolean cache;
        final int maxAge;
        R(Object key, String url, int maxAge, boolean cache, Callback cb) {
            this.key = key;
            this.url = url;
            this.maxAge = maxAge;
            this.cache = cache;
            this.cb = cb;            
        }
        void success(byte[] data) {
            cb.gotData(key, null, data);
        }
        void fail(String msg) {
            cb.gotData(key, msg, null);
        }
    }
    
    static public final int ONE_DAY = 24 * 60 * 60 * 1000;
    
    final Object guard = new Object();
    final Worker worker = new Worker();
    final ArrayDeque queue = new ArrayDeque();
    final Path rootDir;
    
    public HttpCache() { this(Paths.get("Cache")); }
    public HttpCache(Path rootDir) {     
        this.rootDir = rootDir;
        worker.setDaemon(true);
        worker.start();
    }
    
    // ignore cache, download fresh
    // cache if not older than x

    public void waitForQueued() {
        boolean[] g = new boolean[1];
        synchronized (g) {
            synchronized (guard) {
                queue.add((Runnable) () -> {
                    synchronized (g) {
                        g[0] = true;
                        g.notify();
                    }
                });
                guard.notify();
            }
            while (!g[0]) {
                try {
                    g.wait();
                } catch (InterruptedException err) {                    
                }
            }
        }
    }
    
    public void fetchData(Object key, String url, int maxAge, boolean cache, Callback cb) {
        synchronized (guard) {
            queue.add(new R(key, url, maxAge, cache, cb));
            guard.notify();
        }        
    }
    
    public Result fetchData(String url, int maxAge, boolean cache) {
        final Result[] temp = new Result[1];        
        fetchData(null, url, maxAge, cache, (key, error, data) -> {
            synchronized (temp) {                
                temp[0] = new Result(error, data);
                temp.notify();
            }
        });
        synchronized (temp) {
            while (temp[0] == null) {
                try {
                    temp.wait();
                } catch (InterruptedException err) {    
                    // dafuq
                }
            }
            return temp[0];
        }
    }
    
    static public class Result {
        public final String error;
        public final byte[] data;
        Result(String error, byte[] data) {
            this.error = error;
            this.data = data;
        }
    }
    
    
    class Worker extends Thread {        
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    Object msg;
                    synchronized (guard) {
                        while (queue.isEmpty()) {
                            guard.wait();
                        }
                        msg = queue.removeFirst();
                    }
                    if (msg instanceof R) {
                        process((R)msg);
                    } else if (msg instanceof Runnable) {
                        ((Runnable)msg).run();
                    }                    
                }                
            } catch (InterruptedException err) {                
            }            
            System.err.println(this + " death"); // wont happen
        }        
    }
    
    private void process(R req) {
        String u = req.url; 
        int pos = u.indexOf('|'); // we can hide some bullshit options at the end of the url
        String subDir = null;
        String nameOverride = null;
        if (pos >= 0) {
            String rest = u.substring(pos + 1);
            u = u.substring(0, pos);        
            for (String x: rest.split("\\|")) {
                if (x.startsWith("dir=")) { // sub directory                  
                    subDir = x.substring(4);                    
                } else if (x.startsWith("name=")) { // file name override
                    nameOverride = x.substring(5);                    
                }
            }            
            if (nameOverride != null) {
                pos = nameOverride.indexOf("#");
                if (pos >= 0) {
                    nameOverride = nameOverride.replace("#", SHA1.hexString(u));
                }
            }
        }
        URL url;
        try {
            url = new URL(u);
        } catch(MalformedURLException err) {
            req.fail("Bad URL: " + err.getMessage());
            return;
        }
        Path file = null;
        cache: if (req.cache) {
            Path dir = rootDir.resolve(url.getHost());
            if (subDir != null) {
                dir = dir.resolve(subDir);                
            }
            if (nameOverride == null) {
                String path = url.getPath();
                pos = path.lastIndexOf('/');
                if (pos == -1) {
                    break cache;
                }
                path = path.substring(pos + 1);
                if (path.isEmpty()) {
                    break cache;
                }                
                nameOverride = path;
            }
            file = dir.resolve(nameOverride);
            long t;
            try {
                t = System.currentTimeMillis() - Files.getLastModifiedTime(file).toMillis();
            } catch (Exception err) {
                break cache;
            }
            if (req.maxAge == 0 || (req.maxAge > 0 && t <= req.maxAge)) {
                try {
                    req.success(Files.readAllBytes(file));
                    return;
                } catch (IOException err) {
                    // ignore
                }                
            }
        }
        try {
            URLConnection c = url.openConnection();
            String errMsg = null;    
            c.setConnectTimeout(5000);
            c.setRequestProperty("User-Agent", "Mozilla/5.0"); // teehee           
            InputStream in;
            try {
                in = c.getInputStream();
            } catch (FileNotFoundException err) {
                if (c instanceof HttpURLConnection) {
                    in = ((HttpURLConnection)c).getErrorStream();
                    errMsg = err.getMessage();
                } else {
                    throw err;
                }                
            }            
            byte[] data = new byte[4096];            
            int len = 0;
            while (true) {
                int num = in.read(data, len, data.length - len);
                if (num == -1) {
                    break;
                } else if (num > 0) {
                    len += num;
                    if (len == data.length) {
                        data = Arrays.copyOf(data, len * 2);
                    }
                }
            }
            in.close();            
            if (len < data.length) {
                data = Arrays.copyOf(data, len); // size to fit
            }  
            //System.out.println("Downloaded: " + req.url);
            req.cb.gotData(req.key, errMsg, data);   
            if (errMsg == null && file != null) {                
                try {
                    Files.createDirectories(file.getParent());
                    Files.write(file, data);
                } catch (IOException err) {
                    // silent
                }
            }            
        } catch (Exception err) {
            req.fail(err.toString());
        }
    }
    
    
}
