package warbase;

import java.util.Arrays;
import warbase.types.StatT;

public class StatMap {
    

    //long mask;
    static private StatT[] STATS = StatT.db.types;
    
    final int[] total = new int[STATS.length];    
 
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
    
    public boolean isSame(StatMap other) {
        return other != null && Arrays.equals(total, other.total);
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
    
    public int getEffective(StatT stat) {
        if (stat instanceof StatT.Fundamental) {
            int sum = 0;
            for (StatT x: ((StatT.Fundamental)stat).sources) {
                sum += get(x);
            }
            return sum;
        } else {
            return get(stat);
        }        
    }
    
    public int get(StatT stat) {
        return total[stat.index];
    }
    
    public String getDesc(boolean tiny) {
        StringBuilder sb = new StringBuilder();
        appendTo(sb, tiny);
        return sb.toString();
    }
    
    public void appendTo(StringBuilder sb, boolean tiny) {
        boolean first = true;
        for (int i = 0; i < total.length; i++) {
            int t = total[i];
            if (t != 0) { 
                if (first) {
                    first = false;
                } else {
                    sb.append(", ");
                }
                StatT stat = STATS[i];
                stat.appendValue(sb, t);
                sb.append(" ");
                sb.append(tiny ? stat.tinyName : stat.shortName);
            }
        }    
        if (first == true) {
            sb.append("None");
        }
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        appendTo(sb, true);
        sb.append("]");
        return sb.toString();
    }

}
