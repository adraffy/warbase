package com.antistupid.warbase.data;

import com.antistupid.warbase.types.ArmorT;
import com.antistupid.warbase.types.QualityT;
import com.antistupid.warbase.types.WeaponT;

public class DurabilityCurve {
    
    /*
    hands/waist/wrist	25	 30	 35	 40	 30	 35	 40	 45	 35	 40	 50	 55
    feet                    35	 45	 50	 55	 40	 50	 60	 65	 50	 60	 70	 75
    head/shoulder           45	 50	 60	 70	 50	 60	 70	 80	 60	 70	 85	 100
    legs                    55	 65	 75	 85	 65	 75	 90	 100	 75	 90	 105	 120
    chest                   70	 85	 100	 115	 80	 100	 120	 135	 100	 120	 140	 165
    */
    
    static public int get(ArmorT armor, QualityT quality) {
        int offset = 0;
        if (quality == QualityT.RARE || quality == QualityT.HEIRLOOM) {
            offset = 1;
        } else if (quality == QualityT.EPIC || quality == QualityT.EPIC) {
            offset = 2; 
        }
        return 0;
    }
    
    static public int get(WeaponT weapon, QualityT quality) {
        return 0;
    }

}
