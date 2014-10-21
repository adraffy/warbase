package com.antistupid.warbase.structs;

import com.antistupid.warbase.stats.StatMap;
import com.antistupid.warbase.types.StatT;
import com.antistupid.warbase.utils.Misc;

public class StatAlloc {
    
    static public void collectStats(StatMap stats, StatAlloc[] statAllocs, double statBudget) {
        if (statAllocs != null) {
            for (StatAlloc x: statAllocs) {             
                stats.add(x.stat, (int)(0.5 + statBudget * x.alloc));
            }            
        }
    }
    
    static public int compare(StatAlloc[] a, StatAlloc[] b) {
        int c = Misc.compareForNullAtBottom(a, b);
        if (c != 0 || a == null) return c;
        if (a.length != b.length) return Integer.compare(a.length, b.length);
        for (int i = 0; i < a.length; i++) {
            c = compare(a[i], b[i]);
            if (c != 0) return c;
        }
        return 0;
    }
    
    static public int compare(StatAlloc a, StatAlloc b) {
        int c = Misc.compareForNullAtBottom(a, b);
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
        return String.format("%s: Alloc(%d) Mod(%.4f)", stat, alloc, mod);
    }
    
    
    
}
