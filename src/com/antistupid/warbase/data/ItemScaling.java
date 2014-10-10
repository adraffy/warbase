package com.antistupid.warbase.data;

public class ItemScaling {

    static private final short[] V = {
        3,   3,   4,   4,   5,   6,   6,   7,   7,   8,
        8,   9,   9,  10,  10,  11,  11,  12,  12,  13,
       13,  14,  14,  15,  15,  16,  16,  17,  17,  18,
       18,  19,  19,  20,  20,  21,  21,  22,  22,  23,
       23,  24,  24,  25,  25,  26,  26,  27,  27,  28,
       28,  29,  29,  30,  30,  31,  31,  32,  32,  32,
       35,  37,  39,  39,  40,  40,  41,  44,  44,  44,
       44,  44,  45,  46,  49,  49,  50,  50,  51,  51,
       52,  52,  54,  56,  57,  60,  61,  62,  64,  67,
      101, 118, 139, 162, 190, 225, 234, 242, 252, 261
    }; 
    
    public int get(int playerLevel) {
        if (playerLevel < 1) {
            throw new IllegalArgumentException("PlayerLevel Too Low: " + playerLevel);
        } else if (playerLevel > V.length) {
            throw new IllegalArgumentException("PlayerLevel Too High: " + playerLevel);
        } 
        return V[playerLevel - 1];
    }
    
}
