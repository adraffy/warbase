package warbase;

import warbase.types.StatT;

public class StatAlloc {
    
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
