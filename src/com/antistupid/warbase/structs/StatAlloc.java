package com.antistupid.warbase.structs;

import com.antistupid.warbase.types.StatT;

public class StatAlloc {
    
    static public int compare(StatAlloc a, StatAlloc b) {
        if (a == null) {
            return b == null ? 0 : -1;
        } else if (b == null) {
            return 1;
        }
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
