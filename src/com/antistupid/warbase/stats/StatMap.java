package com.antistupid.warbase.stats;

import java.util.Arrays;
import com.antistupid.warbase.types.StatT;

public class StatMap {
    
    static public StatMap recycle(StatMap other) {
        if (other == null) {
            return new StatMap();
        } else {
            other.clear();
            return other;
        }
    }
    
    static public boolean areSame(StatMap a, StatMap b) {
        if (a == null) {
            return b == null;
        } else if (b == null) {
            return false;
        } else {
            return Arrays.equals(a.total, b.total);
        }
    }

    //long mask;
    static public StatT[] STATS = StatT.db.types;
    
    final int[] total = new int[STATS.length];    
 
    public StatMap copy() {
        StatMap copy = new StatMap();
        System.arraycopy(total, 0, copy.total, 0, total.length);
        return copy;
    }
    
    public void add(StatMap other) {
        for (int i = 0; i < total.length; i++) {
            total[i] += other.total[i];
        }
    }
    
    public void add(StatT stat, int value) {        
        total[stat.index] += value;
    }
    
    public void set(StatT stat, int value) {
        total[stat.index] = value;
    }     
    
    public void clear(StatT stat) {
        set(stat, 0);
    }
    
    public void clear() {
        Arrays.fill(total, 0);
    }
    
    public boolean hasAny() {
        for (int i = 0; i < total.length; i++) {
            if (total[i] != 0) {
                return true;
            }
        }
        return false;
    }
    
    public int nonZeroCount() {
        int n = 0;
        for (int i = 0; i < total.length; i++) {
            if (total[i] != 0) {
                n++;
            }
        }
        return n;
    }
    
    
    public int get(StatT stat, boolean effective) {
        return effective ? getEffective(stat) : getRaw(stat);
    } 
    public int getEffective(StatT stat) {
        if (stat instanceof StatT.Fundamental) {
            int sum = 0;
            for (StatT x: ((StatT.Fundamental)stat).sources) {
                sum += getRaw(x);
            }
            return sum;
        } else {
            return getRaw(stat);
        }        
    }   
    public int getRaw(StatT stat) {
        return total[stat.index];
    }
        
    @FunctionalInterface
    static public interface ForEach {
        void apply(StatT stat, int value);
    }
    
    public void forEach(ForEach x) {
        for (int i = 0; i < total.length; i++) {
            int t = total[i];
            if (t != 0) {
                x.apply(STATS[i], t);
            }            
        }
    }
    
    
    // return number of stats added
    public int appendTo(StringBuilder sb, boolean tiny, boolean noneIfEmpty) {
        int num = 0;
        for (int i = 0; i < total.length; i++) {
            int t = total[i];
            if (t != 0) { 
                if (num++ > 0) {
                    sb.append(", ");
                }
                StatT stat = STATS[i];
                stat.appendValue(sb, t);
                sb.append(" ");
                sb.append(tiny ? stat.tinyName : stat.shortName);
            }
        }    
        if (num == 0 && noneIfEmpty) {
            sb.append("None");
        }
        return num;
    }
    
    public String toString_noBracket(boolean tiny) {
        StringBuilder sb = new StringBuilder();
        appendTo(sb, tiny, true);
        return sb.toString();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        appendTo(sb, true, false);
        sb.append("]");
        return sb.toString();
    }

}
