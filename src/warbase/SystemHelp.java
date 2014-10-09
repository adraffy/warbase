package warbase;

import java.awt.Desktop;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.nio.file.Path;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class SystemHelp {
    
    static public final boolean MAC;
    static public final boolean WIN;
    static {
        String sys = System.getProperty("os.name");
        MAC = sys.contains("Mac") || sys.contains("OS X");
        WIN = sys.contains("Windows");
    }
           
    static public final int ACCELERATOR_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
    
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
    
    
    static public boolean openFile(Object obj) {
        File f;
        if (obj instanceof File) {
            f = (File)obj;
        } else if (obj instanceof Path) {
            f = ((Path)obj).toFile();
        } else if (obj instanceof String) {
            f = new File(obj.toString());
        } else {
            return false;
        }
        if (WIN && f.isDirectory()) {
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
            } catch (IOException err) {
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
