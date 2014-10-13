package com.antistupid.warbase.utils;

import java.awt.Image;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import javax.swing.JMenuBar;
import javax.swing.JTextField;

public class MacHelp {
    
    static public final char GLYPH = '⌘';
    
    // https://developer.apple.com/library/mac/documentation/Java/Conceptual/Java14Development/07-NativePlatformIntegration/NativePlatformIntegration.html

    static public void init() {     
        if (!SystemHelp.MAC) return;
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        //System.setProperty("com.apple.mrj.application.apple.menu.about.name", "PPOOP");
    }
    
    static Object app() throws Exception {
        return Class.forName("com.apple.eawt.Application").getMethod("getApplication", (Class[]) null).invoke(null, (Object[]) null);
    }
    
    static Object proxy(String className, InvocationHandler h) throws Exception {
        return Proxy.newProxyInstance(Class.forName(className).getClassLoader(), new Class[] { Class.forName(className) }, h);
    }
    
    static public void registerAbout(Runnable r) {
        if (!SystemHelp.MAC) return;
        try {
            Object app = app();
            Object al = proxy("com.apple.eawt.AboutHandler", (proxy, method, args) -> {
                r.run();
                return null;
            });
            app.getClass().getMethod("setAboutHandler", new Class[] { Class.forName("com.apple.eawt.AboutHandler") }).invoke(app, new Object[] { al });                
        } catch (Exception e) {
            //fail quietly
        }
    }
    
    static public void registerPrefs(Runnable r) {
        if (!SystemHelp.MAC) return;
        try {
            Object app = app();
            Object al = proxy("com.apple.eawt.PreferencesHandler", (proxy, method, args) -> {
                r.run();
                return null;
            });
            app.getClass().getMethod("setPreferencesHandler", new Class[] { Class.forName("com.apple.eawt.PreferencesHandler") }).invoke(app, new Object[] { al });                
        } catch (Exception e) {
            //fail quietly
        }
    }
    
    static public void setDefaultMenuBar(JMenuBar menuBar) {
        if (!SystemHelp.MAC) return;
        try {
            Object app = app();
            app.getClass().getMethod("setDefaultMenuBar", new Class[] { JMenuBar.class }).invoke(app, new Object[] { menuBar });                
        } catch (Exception e) {
            //fail quietly
        }
    }
    
    static public void setDockIcon(Image image) {
        if (!SystemHelp.MAC) return;
        try {
            Object app = app();
            app.getClass().getMethod("setDockIconImage", new Class[] { Image.class }).invoke(app, new Object[] { image });                
        } catch (Exception e) {
            //fail quietly
        }
    }
    
    static public void makeSearchField(JTextField field, String promptText) {
        if (!SystemHelp.MAC) return;
        field.putClientProperty("JTextField.variant", "search");    
        field.putClientProperty("JTextField.Search.Prompt", promptText);
    }
    
}
