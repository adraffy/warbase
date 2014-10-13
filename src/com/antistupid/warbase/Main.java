package com.antistupid.warbase;

import com.antistupid.warbase.data.HealthCurve;
import com.antistupid.warbase.data.ManaCurve;
import com.antistupid.warbase.data.RatingCurve;
import com.antistupid.warbase.stats.StatMap;
import com.antistupid.warbase.stats.CompactBaseStats;
import com.antistupid.warbase.types.GemT;
import com.antistupid.warbase.types.RaceT;
import com.antistupid.warbase.types.SocketT;
import com.antistupid.warbase.types.StatT;
import com.antistupid.warbase.types.ClassT;
import com.antistupid.warbase.types.RatingT;
import com.antistupid.warbase.types.SpecT;

public class Main {

    public static void main(String[] args) {
 
        //System.out.println(EquipT.CHEST.)
        
        for (RatingT x: RatingT.db.types) {
            System.out.println(x + ": " + x.getCoeff(100));
        }
        
        System.out.println(ManaCurve.get(1, SpecT.FERAL));
        
        System.out.println(CompactBaseStats.getStats(RaceT.NE.compactBaseStats));
        //System.out.println(CompactBaseStats.getStats(RaceT.ORC.compactBaseStats));
        //System.out.println(CompactBaseStats.getStats(ClassT.WAR.getCompactBaseStats(100)));
  
        StatMap map = new StatMap();
        CompactBaseStats.collectStats(map, RaceT.NE.compactBaseStats);
        CompactBaseStats.collectStats(map, ClassT.DRUID.getCompactBaseStats(100));
        System.out.println(map);
        
        
        System.out.println(ClassT.DRUID.specs);
        System.out.println(ClassT.DRUID.races);
        System.out.println(RaceT.NE.getClasses());
        System.out.println(RaceT.NE.getSpecs());
        
        System.out.println(StatT.db.size());
                
        System.out.println("Red gem matches sockets: " + GemT.RED.sockets(true));
        // Red gem matches sockets: [Red, Prismatic]
        
        System.out.println("Red gem fits in sockets: " + GemT.RED.sockets(false));
        // Red gem fits in sockets: [Red, Yellow, Blue, Prismatic] 
        
        System.out.println("Red socket matches gems: " + SocketT.RED.gems(true));
        // Red socket matches gems: [Red, Purple, Orange, Prismatic]
        
        System.out.println("Red socket accepts gems: " + SocketT.RED.gems(false));
        // Red socket accepts gems: [Red, Blue, Yellow, Purple, Green, Orange, Prismatic]
    
        ///System.out.println(ArmorCurve.get(586, QualityT.GREEN, ArmorT.LEATHER, EquipT.SHOULDER));
        // 73
        
        //System.out.println(ArmorCurve.get(615, QualityT.BLUE, ArmorT.LEATHER, EquipT.LEGS));
        // 109
        
    }
    
}