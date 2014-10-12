package com.antistupid.warbase.types;

import java.util.ArrayList;

public class GemT extends TypeT {

    final int mask;
    
    private GemT(int id, String name, int colorMask) {
        super(id, id, name);
        this.mask = colorMask;
    }
    
    public ArrayList<SocketT> sockets(boolean bonus) {
        return SocketT.db.selectList(x -> x.matches(this, bonus));
    }
    
    public boolean socketable() {
        return mask != 0;
    }

    static public final GemT RED         = new GemT(0, "Red",      GemBits.RED);
    static public final GemT BLUE        = new GemT(1, "Blue",     GemBits.BLUE);
    static public final GemT YELLOW      = new GemT(2, "Yellow",   GemBits.YELLOW);
    static public final GemT PURPLE      = new GemT(3, "Purple",   GemBits.PURPLE);
    static public final GemT GREEN       = new GemT(4, "Green",    GemBits.GREEN);
    static public final GemT ORANGE      = new GemT(5, "Orange",   GemBits.ORANGE);
    static public final GemT META        = new GemT(6, "Meta",     GemBits.META);
    static public final GemT MATERIAL    = new GemT(7, "Material", 0);
    static public final GemT PRISMATIC   = new GemT(8, "Prismatic",GemBits.PRISMATIC);
    static public final GemT SHA         = new GemT(9, "Sha",      GemBits.SHA);
    static public final GemT COG         = new GemT(10,"Cog",      GemBits.COG);
    
    static public final BitContainer<GemT> db = new BitContainer<>(GemT.class);
    
}
