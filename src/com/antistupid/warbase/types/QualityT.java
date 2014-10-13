package com.antistupid.warbase.types;

import java.awt.Color;

public class QualityT extends TypeT {

    public final Color fillColor;
    public final Color textColor;
    public final double armorMod;
    public final int randPropArrayIndex;
    public final int damageIndex;
    
    public QualityT(int id, String name, Color fillColor, Color textColor, double armorMod, int randPropArrayIndex, int damageIndex) {
        super(id, id, name);
        this.fillColor = fillColor;
        this.textColor = textColor != null ? textColor : fillColor;
        this.armorMod = armorMod;
        this.randPropArrayIndex = randPropArrayIndex;
        this.damageIndex = damageIndex;
    }

    static public final QualityT POOR       = new QualityT(0, "Poor", new Color(128, 128, 128), null, 0.90, -1, 0);
    static public final QualityT COMMON     = new QualityT(1, "Common", new Color(150, 75, 0), Color.BLACK, 0.95,   -1, 1);
    static public final QualityT GREEN      = new QualityT(2, "Uncommon", new Color(30, 204, 0), null, 1.00, 2, 2);
    static public final QualityT BLUE       = new QualityT(3, "Rare", new Color(0, 112, 221), null, 1.00, 1, 3);
    static public final QualityT PURPLE     = new QualityT(4, "Epic", new Color(163, 53, 238), null, 1.00, 0, 4);
    static public final QualityT ORANGE     = new QualityT(5, "Legendary", new Color(255, 128, 0), null, 1.00, 0, 4);
    static public final QualityT ARTIFACT   = new QualityT(6, "Artifact", Color.RED, null,  1.00, 0, 4);
    static public final QualityT HEIRLOOM   = new QualityT(7, "Heirloom", new Color(229, 204, 128), new Color(192, 171, 107), 1.00, 1, 3);
    
    static public final BitContainer<QualityT> db = new BitContainer<>(QualityT.class);
        
    static public QualityT max(QualityT a, QualityT b) {
        return a == null ? b : b == null ? a : a.id > b.id ? a : b;
    }
    
    
}
