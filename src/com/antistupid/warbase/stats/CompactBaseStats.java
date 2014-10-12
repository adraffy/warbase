package com.antistupid.warbase.stats;

import com.antistupid.warbase.types.StatT;
import java.util.function.ToIntFunction;

public class CompactBaseStats {
    
    static public final StatT[] STATS = {StatT.STA, StatT.STR, StatT.AGI, StatT.INT, StatT.SPI};
    static private final int[] quickIndex = StatT.db.createFastLookup(STATS);

    static private final int BITS = 12; // 5 * 12 <= 64
    static public final int RANGE = (1 << BITS) - 1;
    static public final int MIN = -(1 << (BITS - 1));
    static public final int MAX = MIN + RANGE;

    static private int unshift(long bits, int index) {
        int value = (int)((bits >>> (index * BITS)) & RANGE);
        return value > MAX ? value - RANGE : value;
    }
    
    static private long shift(int index, int value) {
        return (long)value << (index * BITS);
    }
    
    static public boolean isCompact(int value) {
        return value >= MIN && value <= MAX;
    }
        
    static public int[] toArray(long bits) {
        int[] temp = new int[STATS.length];
        for (int i = 0; i < STATS.length; i++) {            
            temp[i] = unshift(bits, i);
        }        
        return temp;
    }
    
    static public StatMap getStats(long bits) {
        StatMap temp = new StatMap();
        collectStats(temp, bits);
        return temp;
    }
    
    static public void collectStats(StatMap stats, long bits) {
        for (int i = 0; i < STATS.length; i++) {            
            stats.add(STATS[i], unshift(bits, i));
        }  
    }
    
    static public int getStat(StatT stat, long bits) {
        int idx = quickIndex[stat.index];
        return idx >= 0 ? unshift(bits, idx) : 0;
    }
    
    static public long encode(int[] stats) {
        long bits = 0;
        for (int i = 0; i < STATS.length; i++) {   
            int value = stats[i];
            if (!isCompact(value)) {
                throw new IllegalArgumentException("Illegal Stat Value: " + STATS[i].formatValue(value));
            }
            bits |= shift(i, value);
        }
        return bits;
    }
    
    static public long encode(StatMap stats) { return encode(stats::get); }    
    static public long encode(ToIntFunction<StatT> f) {
        long bits = 0;
        for (int i = 0; i < STATS.length; i++) {   
            int value = f.applyAsInt(STATS[i]);
            if (!isCompact(value)) {
                throw new IllegalArgumentException("Illegal Stat Value: " + STATS[i].formatValue(value));
            }
            bits |= shift(i, value);
        }
        return bits;
    }
    
    
}
