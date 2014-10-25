package com.antistupid.warbase.data;

import com.antistupid.warbase.types.ClassT;
import com.antistupid.warbase.types.SpecT;
import com.antistupid.warbase.types.StatT;

public class ManaCurve {
    
    static final int[] HYBRID = {31, 34, 36, 42, 71, 101, 104, 137, 140, 173, 176, 212, 220, 252, 256, 292, 298, 334, 362, 400, 420, 460, 480, 520, 580, 620, 620, 660, 740, 780, 780, 840, 880, 940, 980, 1020, 1040, 1080, 1180, 1240, 1240, 1300, 1360, 1420, 1480, 1540, 1600, 1660, 1720, 1780, 1840, 1920, 1940, 2040, 2060, 2140, 2200, 2280, 2360, 2420, 2600, 2760, 2920, 2940, 3020, 3020, 3080, 3100, 3260, 3380, 3520, 3700, 3860, 3880, 4060, 4180, 4360, 4600, 4680, 4880, 5100, 5280, 5520, 5780, 6000, 6400, 6600, 6800, 7200, 7400, 10200, 13000, 15600, 19400, 24000, 27600, 28800, 30000, 31000, 32000};
    static final int[] PRIMARY = {155, 170, 180, 210, 355, 505, 520, 685, 700, 865, 880, 1060, 1100, 1260, 1280, 1460, 1490, 1670, 1810, 2000, 2100, 2300, 2400, 2600, 2900, 3100, 3100, 3300, 3700, 3900, 3900, 4200, 4400, 4700, 4900, 5100, 5200, 5400, 5900, 6200, 6200, 6500, 6800, 7100, 7400, 7700, 8000, 8300, 8600, 8900, 9200, 9600, 9700, 10200, 10300, 10700, 11000, 11400, 11800, 12100, 13000, 13800, 14600, 14700, 15100, 15100, 15400, 15500, 16300, 16900, 17600, 18500, 19300, 19400, 20300, 20900, 21800, 23000, 23400, 24400, 25500, 26400, 27600, 28900, 30000, 32000, 33000, 34000, 36000, 37000, 51000, 65000, 78000, 97000, 120000, 138000, 144000, 150000, 155000, 160000};

    static private void checkLevel(int playerLevel) {
        if (playerLevel < 1) {
            throw new IllegalArgumentException("Illegal Player Level: " + playerLevel);
        } 
    }
    
    static public int getMax_primary(int playerLevel) {
        checkLevel(playerLevel);
        return PRIMARY[Math.min(PRIMARY.length, playerLevel) - 1];
    }
    
    static public int getMax_hybrid(int playerLevel) {
        checkLevel(playerLevel);
        return HYBRID[Math.min(HYBRID.length, playerLevel) - 1];
    }
    
    static public int getMax(int playerLevel, SpecT spec) {
        checkLevel(playerLevel);
        if (spec == null) {
            return 0;
        } else if (spec.primaryStat == StatT.INT) {
            return PRIMARY[Math.min(PRIMARY.length, playerLevel) - 1];
        } else if (spec.manaHybrid) {
            return HYBRID[Math.min(HYBRID.length, playerLevel) - 1];
        } else {
            return 0;
        }        
    }
    
    
    static public final double REGEN_PER_MANA_CASTER = 0.008244141;
    static public final double REGEN_PER_MAMA_HYBRID = 0.0020610352;
    
    
    static public double getManaAttunement(SpecT spec) {        
        if (spec == SpecT.FERAL || spec == SpecT.GUARDIAN) {
            return 2; // mana attunement: +100%
        } else if (spec != null && spec.classType == ClassT.MAGE) {
            return 4.5; // mana attunement: +350%
        } else {
            return 1;
        }
    }
    
    // 100
    // 4815 @ 784 spi
    // 6431 @ 192k mana
    
    // 100
    // 1280 @ 782
    // 7726 @ 32k mana
    
    // 90
    // 296 @ 220
    // 2109 @ 7400 mana
    
    static public double getSpiritRegen(int spirit) {
        return 0;
    }
    
    static public double getRegenPerMana(int playerLevel, SpecT spec) {
        //0.8%/sec for level 1-90.
        //Drops by 0.04%/sec per level past 90.
        //0.4%/sec at level 100.        
        
        if (playerLevel <= 90) {
            return 0.008;
        } else if (playerLevel >= 100) {
            return 0.004;
        } else {
            return 0.008 - 0.004 * (playerLevel - 90);
        }
    }    
    
}
