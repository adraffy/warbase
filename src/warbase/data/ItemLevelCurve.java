package warbase.data;

import java.util.Arrays;
import java.util.TreeMap;

public class ItemLevelCurve {

    
    static private final TreeMap<Integer,int[]> C = new TreeMap<>();
    static {
        C.put(956,new int[]{1,57,58,67,68,80,81,85,86,90,91,100,6,62,79,105,139,187,279,333,384,463,543,633,});
        C.put(959,new int[]{1,57,58,67,68,80,81,85,86,90,100,6,62,79,105,139,187,279,333,384,548,620,});
        C.put(960,new int[]{1,57,58,67,68,80,81,85,86,90,100,6,62,79,105,139,187,279,333,384,561,620,});
        C.put(961,new int[]{1,57,58,67,68,80,81,85,86,90,100,6,62,79,105,139,187,279,333,384,574,620,});
    }
    

    static public int getItemLevel(int curveId, int playerLevel) {        
        if (playerLevel < 1) {
            throw new IllegalArgumentException("Illegal Player Level: " + playerLevel);
        }
        int[] curve = C.get(curveId);
        if (curve == null) {
            throw new IllegalArgumentException("Unknown Item Curve: " + curveId);
        }       
        int half = curve.length >> 1;
        int at = Arrays.binarySearch(curve, 0, half, playerLevel);
        if (at >= 0) {
            return curve[at + half];
        } 
        at = ~at;
        if (at == 0) {
            throw new IllegalArgumentException(String.format("PlayerLevel(%d) Below Curve(%d) Bound(%d)", playerLevel, curveId, curve[half]));
        } else if (at == half) {
            throw new IllegalArgumentException(String.format("PlayerLevel(%d) Above Curve(%d) Bound(%d)", playerLevel, curveId, curve[half + half - 1]));
        }
        double ilvl = curve[at + half - 1];        
        double level = curve[at - 1];        
        return (int)(ilvl + (curve[at + half] - ilvl) * (playerLevel - level) / (curve[at] - level));   
    }
    
        
}
