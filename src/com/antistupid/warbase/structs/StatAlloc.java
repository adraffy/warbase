package com.antistupid.warbase.structs;

import com.antistupid.warbase.data.PlayerScaling;
import com.antistupid.warbase.stats.StatMap;
import com.antistupid.warbase.types.StatT;
import com.antistupid.warbase.utils.CompareHelp;

public class StatAlloc {
    
    static public void collectItemStats(StatMap stats, StatAlloc[] statAllocs, double statBudget) {
        if (statAllocs != null) {
            for (StatAlloc x: statAllocs) {             
                stats.add(x.stat, (int)(0.5 + statBudget * x.alloc));
            }            
        }
    }
    
    static public void collectSpellStats(StatMap stats, StatAlloc[] statAllocs, int playerLevel, int scalingLevelMin, int scalingLevelMax, int scalingId) {
        if (statAllocs != null) {
            int lvl = PlayerScaling.max(scalingLevelMax, playerLevel);
            float scaling = PlayerScaling.get(Math.max(scalingLevelMin, lvl), scalingId);
            /*if (perLevel > 0 && lvl > min) {                
                scaling *= (min + perLevel * (lvl - min)) / lvl;                
            } */              
            for (StatAlloc x: statAllocs) {
                if (x.mod == 0) {
                    stats.add(x.stat, x.alloc);
                } else {
                    int value = (int)(0.5 + scaling * x.mod);
                    stats.add(x.stat, value);
                }
            }
        }
    }
    
    static public int compare(StatAlloc[] a, StatAlloc[] b) {
        int c = CompareHelp.compareForNullAtBottom(a, b);
        if (c != 0 || a == null) return c;
        if (a.length != b.length) return Integer.compare(a.length, b.length);
        for (int i = 0; i < a.length; i++) {
            c = compare(a[i], b[i]);
            if (c != 0) return c;
        }
        return 0;
    }
    
    static public int compare(StatAlloc a, StatAlloc b) {
        int c = CompareHelp.compareForNullAtBottom(a, b);
        if (c != 0 || a == null) return c;
        if (a.stat != b.stat) {
            return Integer.compare(a.stat.index, b.stat.index);
        }
        if (a.alloc != b.alloc) {
            return Integer.compare(a.alloc, b.alloc);
        }
        if (a.mod != b.mod) {
            return Float.compare(a.mod, b.mod);
        }
        return 0;
    }
    
    public final StatT stat;
    public final int alloc;
    public final float mod;

    public StatAlloc(StatT stat, int alloc, float mod) {
        this.stat = stat;
        this.alloc = alloc;
        this.mod = mod;
    }
    
    @Override
    public String toString() {
        return String.format("%s<%d,%.4f>", stat, alloc, mod);
    }
    
    
    
}
