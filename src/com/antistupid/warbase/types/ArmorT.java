package com.antistupid.warbase.types;

public class ArmorT extends TypeT {

    public final int armorTotalIndex;

    private ArmorT(int id, String name, int armorTotalIndex) {
        super(id, id, name);
        this.armorTotalIndex = armorTotalIndex;
    }
           
    static public final ArmorT NONE      = new ArmorT(0, "None",    -1);
    static public final ArmorT CLOTH     = new ArmorT(1, "Cloth",    0);
    static public final ArmorT LEATHER   = new ArmorT(2, "Leather",  1);
    static public final ArmorT MAIL      = new ArmorT(3, "Mail",     2);
    static public final ArmorT PLATE     = new ArmorT(4, "Plate",    3);
    static public final ArmorT COSMETIC  = new ArmorT(5, "Cosmetic",-1);
    static public final ArmorT SHIELD    = new ArmorT(6, "Shield",  -1);

    static public final BitContainer<ArmorT> db = new BitContainer<>(ArmorT.class);
    static public final BlizzBit<ArmorT> blizzBits = new BlizzBit<>(ArmorT.class);
    
    static public final long TRIVIAL = db.encode(NONE, COSMETIC);    
    
}
