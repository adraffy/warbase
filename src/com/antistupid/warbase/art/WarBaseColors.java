package com.antistupid.warbase.art;

import com.antistupid.warbase.types.ClassT;
import com.antistupid.warbase.types.QualityT;
import java.awt.Color;

public class WarBaseColors {

    // we have to load this outside
    // of the types because fucking 
    // java loads up awt bullshit
    // when color is used, jesus christ
    
    static private final Color[] QUALITY_FILL_COLOR = {
        new Color(128, 128, 128),
        new Color(150, 75, 0),
        new Color(30, 204, 0),
        new Color(0, 112, 221),
        new Color(163, 53, 238),
        new Color(255, 128, 0),
        Color.RED,
        new Color(229, 204, 128)        
    };
    
    static public Color getQualityFillColor(QualityT q) {
        return q == null ? null : QUALITY_FILL_COLOR[q.index];
    }
    
    static private final Color[] QUALITY_TEXT_COLOR = {
        new Color(128, 128, 128),
        Color.BLACK, // much darker
        new Color(30, 204, 0),
        new Color(0, 112, 221),
        new Color(163, 53, 238),
        new Color(255, 128, 0),
        Color.RED,
        new Color(192, 171, 107) // darker
    };
    
    static public Color getQualityTextColor(QualityT q) {
        return q == null ? null : QUALITY_TEXT_COLOR[q.index];
    }
    
    
    static private final Color[] CLASS_FILL_COLOR = {
        new Color(0xC6, 0x9B, 0x6D),
        new Color(0xF4, 0x8C, 0xBA),
        new Color(0xAA, 0xD3, 0x72),
        new Color(0xFF, 0xF4, 0x68),
        Color.WHITE,
        new Color(0xC4, 0x1E, 0x3B),
        new Color(0x47, 0x74, 0xFF), //0x23, 0x59, 0xFF
        new Color(0x68, 0xCC, 0xEF),
        new Color(0x93, 0x82, 0xC9),
        new Color(0x00, 0x84, 0x67),
        new Color(0xFF, 0x7C, 0x0A)		             
    };
    
    static public Color getClassFillColor(ClassT c) {
        return c == null ? null : CLASS_FILL_COLOR[c.index];        
    }
    
    static private final Color[] CLASS_TEXT_COLOR = CLASS_FILL_COLOR.clone();
    static {
        CLASS_TEXT_COLOR[ClassT.PRIEST.index] = Color.BLACK;
    }
        
    static public Color getClassTextColor(ClassT c) {
        return c == null ? null : CLASS_TEXT_COLOR[c.index];        
    }
    
}

