package com.antistupid.warbase.icons;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;

public class RetinaIcon extends ImageIcon {

    public RetinaIcon(Image image) {
        super(image);
    }

    @Override
    public synchronized void paintIcon(Component c, Graphics gg, int x, int y) {
        ImageObserver observer = getImageObserver();
        if (observer == null) {
            observer = c;
        }
        Image image = getImage();
        int width = image.getWidth(observer);
        int height = image.getHeight(observer);
        Graphics2D g = (Graphics2D)gg.create(x, y, width, height);
        //Graphics2D g = (Graphics2D)gg;
        g.scale(0.5, 0.5);
        g.drawImage(image, 0, 0, observer);
        g.dispose();
    }
    
    @Override
    public int getIconWidth() {
        return super.getIconWidth() / 2;
    }
    
    @Override
    public int getIconHeight() {
        return super.getIconHeight() / 2;
    }

}
