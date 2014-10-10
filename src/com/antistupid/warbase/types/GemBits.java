package com.antistupid.warbase.types;

public class GemBits {
    
    static public final int META    = 1 << 0; //0x0001; //1 << Index.META = 0
    static public final int RED     = 1 << 1; //0x0002; //1 << Index.RED = 1
    static public final int YELLOW  = 1 << 2; //Index.YELLOW;
    static public final int BLUE    = 1 << 3; //Index.BLUE;
    static public final int SHA     = 1 << 4; //Index.SHA;
    static public final int COG     = 1 << 5; //Index.COG;        
    static public final int GREEN = YELLOW | BLUE;
    static public final int ORANGE = YELLOW | RED;
    static public final int PURPLE = BLUE | RED;
    static public final int PRISMATIC = YELLOW | BLUE | RED;

}
