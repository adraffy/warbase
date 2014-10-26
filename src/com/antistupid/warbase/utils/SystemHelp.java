package com.antistupid.warbase.utils;

import java.awt.Desktop;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SystemHelp {
    
    static public final boolean MAC;
    static public final boolean WIN;
    static {
        String sys = System.getProperty("os.name");
        MAC = sys.contains("Mac") || sys.contains("OS X");
        WIN = sys.contains("Windows");
    }
    
    static public final Path HOME_DIR = Paths.get(System.getProperty("user.home"));
    static public final Path DATA_DIR;
    static {
        if (MAC) {
            DATA_DIR = HOME_DIR.resolve("Library").resolve("Application Support");
        } else if (WIN) {
            DATA_DIR = HOME_DIR.resolve("Local Settings").resolve("ApplicationData");
        } else {
            DATA_DIR = HOME_DIR;
        }        
    }
         
    // we cant statically define this because this bullshit invokes
    // awt/toolkit init which causes bullshit startup delays for headless apps
    static public boolean isRetina() { 
        GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        try {
            Field field = graphicsDevice.getClass().getDeclaredField("scale");
            if (field != null) {
                field.setAccessible(true);
                Object scale = field.get(graphicsDevice);
                return scale instanceof Integer && ((Integer)scale) == 2;
            }
            return false;
	} catch (Exception e) { 
            return false;
	}
    }
    
    static private File coerceFile(Object obj) {        
        if (obj instanceof File) {
            return (File)obj;
        } else if (obj instanceof Path) {
            return ((Path)obj).toFile();
        } else if (obj instanceof String) {
            return new File(obj.toString());
        } else {
            return null;
        }
    }
    
    static public boolean showFile(Object obj) {
        File f = coerceFile(obj);
        if (f == null) {
            return false;
        } else if (!f.isDirectory()) {
            if (WIN) {
                try {
                    Process proc = Runtime.getRuntime().exec("explorer /root," + f.getAbsolutePath());
                    try {
                        proc.waitFor();
                    } catch (InterruptedException stfu) {
                    }
                    return true;
                } catch (IOException err) {
                    return false;
                }
            } else if (MAC) {
                try {
                    ProcessBuilder pb = new ProcessBuilder("/usr/bin/open", "-R", f.getAbsolutePath());                    
                    Process proc = pb.start();
                    try {
                        proc.waitFor();
                    } catch (InterruptedException stfu) {
                    }
                    return true;
                } catch (IOException err) {
                    return false;
                }
            }
        }
        return openFile(f);
    }
    
    static public boolean openFile(Object obj) {
        File f = coerceFile(obj);
        if (f == null) {
            return false;
        } else if (WIN && f.isDirectory()) {
            try {
                Process proc = Runtime.getRuntime().exec("explorer /root," + f.getAbsolutePath());
                try {
                    proc.waitFor();
                } catch (InterruptedException stfu) {
                }
                return true;
            } catch (IOException err) {
                return false;
            }
        } else {
            try {
                Desktop.getDesktop().open(f);
                return true;
            } catch (Exception err) {
                return false;
            }
        }
    }
    
    static public boolean openURL(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
            return true;
        } catch (Exception err) {
            return false;
        }
    }  
    
}
