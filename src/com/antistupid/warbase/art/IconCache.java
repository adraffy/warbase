package com.antistupid.warbase.art;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.antistupid.warbase.HttpCache;
import com.antistupid.warbase.utils.SystemHelp;

public class IconCache {

    @FunctionalInterface
    static public interface Callback {
        void got(String key, String error, ImageIcon icon);
    }
    
    static public Callback swingCallback(Consumer<ImageIcon> c) {
        return (key, error, icon) -> {
            if (SwingUtilities.isEventDispatchThread()) {
                c.accept(icon);
            } else {
                SwingUtilities.invokeLater(() -> c.accept(icon));   
            }            
            //SwingUtilities.invokeLater(() -> c.accept(icon));   
        };
    }
    
    static private final ImageIcon requested = new ImageIcon();
    
    private final TreeMap<String,ImageIcon> iconMap = new TreeMap<>();    
    private final TreeMap<String,HashSet<Callback>> cbMap = new TreeMap<>();
    
    private final Object guard = new Object();
    
    private ImageIcon blank;
    
    public final int size;
    public final int scale;
    public final HttpCache hc;
    public final String urlFmt; // single $ replacement
    
    // offical blizzard icon source
    
    static public IconCache blizz18(HttpCache hc) { return new IconCache(hc, 18, 1, "http://media.blizzard.com/wow/icons/18/$.jpg|dir=18"); }
    static public IconCache blizz38(HttpCache hc) { return new IconCache(hc, 36, 1, "http://media.blizzard.com/wow/icons/18/$.jpg|dir=36"); }
    static public IconCache blizz58(HttpCache hc) { return new IconCache(hc, 56, 1, "http://media.blizzard.com/wow/icons/18/$.jpg|dir=56"); }
    
    static public IconCache blizz18_retinaSupport(HttpCache hc) {
        if (SystemHelp.isRetina()) {
            return new IconCache(hc, 18, 2, "http://media.blizzard.com/wow/icons/18/$.jpg|dir=36");
        } else {
            return blizz18(hc);
        }
    }
    
    // wowhead icon source
    
    static public IconCache zam18(HttpCache hc) { return new IconCache(hc, 18, 1, "http://wow.zamimg.com/images/wow/icons/small/$.jpg|dir=18"); }
    static public IconCache zam36(HttpCache hc) { return new IconCache(hc, 36, 1, "http://wow.zamimg.com/images/wow/icons/medium/$.jpg|dir=36"); }
    static public IconCache zam56(HttpCache hc) { return new IconCache(hc, 56, 1, "http://wow.zamimg.com/images/wow/icons/large/$.jpg|dir=56"); }
    
    static public IconCache zam18_retinaSupport(HttpCache hc) {
        if (SystemHelp.isRetina()) {
            return new IconCache(hc, 18, 2, "http://wow.zamimg.com/images/wow/icons/medium/$.jpg|dir=36");
        } else {
            return zam18(hc);
        }
    }
    
    // ---
    
    public IconCache(HttpCache hc, int size, int scale, String urlFmt) {
        this.hc = hc;
        this.size = size;        
        this.scale = scale;
        this.urlFmt = urlFmt;
        
        int s = size * scale;
        BufferedImage img = new BufferedImage(s, s, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();     
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, s, s);        
        g.setColor(Color.WHITE);        
        String q = "?";
        Font f = UIManager.getFont("Label.font");
        FontMetrics m;
        while (true) {
            m = g.getFontMetrics(f);
            if (m.getHeight() >= s) {
                break;
            }
            f = f.deriveFont(f.getSize2D() + 1);
        }
        g.setFont(f);
        g.drawString(q, (s - m.stringWidth(q)) / 2f, (s - m.getHeight()) / 2f + m.getAscent());        
        g.dispose();        
        blank = toImageIcon(img);
    }
    
    private ImageIcon toImageIcon(BufferedImage image) {
        return scale > 1 ? new RetinaIcon(image) : new ImageIcon(image);
    }
    
    public ImageIcon getBlank() {
        synchronized (guard) {
            return blank;
        }
    }
    
    public void useOfficialBlank() {
        get("inv_misc_questionmark", false, (key, error, icon) -> {
            if (icon != null) {
                synchronized (guard) {
                    blank = icon;
                }
            }            
        });
    }
        
    public void get(String key, Callback cb) { get(key, true, cb); }
    public void get(String key, boolean triggerBlank, Callback cb) { // do we need to support null cb?  
        synchronized (guard) {
            if (key == null) {
                if (triggerBlank) {
                    cb.got(key, null, blank);
                }
                return;
            }
            ImageIcon store = iconMap.get(key);
            if (store != null && store != requested) {
                cb.got(key, null, store);    
                return;
            }  
            if (store == null) {
                iconMap.put(key, requested);                
                hc.fetchData(key, urlFmt.replace("$", key), 0, true, this::gotData);                
            }          
            HashSet<Callback> set = cbMap.get(key);
            if (set == null) {
                set = new HashSet<>();
                cbMap.put(key, set);
            }
            set.add(cb);    
            if (triggerBlank) {
                cb.got(key, null, blank);
            }
        }
    } 
    
    // note this is called from httpcache thread
    void gotData(Object key0, String error, byte[] data) {
        String key = (String)key0;
        ImageIcon result = blank;
        if (error == null) {       
            try {
                BufferedImage img = ImageIO.read(new ByteArrayInputStream(data));
                int s = size * scale;
                if (img.getWidth() == s && img.getHeight() == s) {
                    result = toImageIcon(img);
                } else {
                    error = "Wrong Size";
                }     
            } catch (IOException err) {
                error = "Image Read Error: " + err.getMessage();
            }    
        }        
        HashSet<Callback> set;
        synchronized (guard) {
            iconMap.put(key, result);
            set = cbMap.remove(key);
        }
        if (set != null) {
            //System.out.println(key0 + ":"  + set.size());
            for (Callback x: set) {
                x.got(key, error, result);
            }
        }        
    }
    
    /*
    private static class RetinaIcon extends ImageIcon {

        public RetinaIcon(final Image image) {
            super(image);
        }

        @Override
        public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
            ImageObserver observer = getImageObserver();
            if (observer == null) {
                observer = c;
            }
            Image image = getImage();
            int width = image.getWidth(observer);
            int height = image.getHeight(observer);
            final Graphics2D g2d = (Graphics2D)g.create(0, 0, width, height);
            g2d.scale(0.5, 0.5);
            System.out.println(observer);
            g2d.drawImage(image, 0, 0, observer);
            g2d.scale(1, 1);
            g2d.dispose();
        }
        
    }
    */
    
}
