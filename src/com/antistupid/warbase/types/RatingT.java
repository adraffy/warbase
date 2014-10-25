package com.antistupid.warbase.types;

import com.antistupid.warbase.data.RatingCurve;
import com.antistupid.warbase.ids.CombatRatingId;

public class RatingT extends TypeT {
    
    static private int __index;
    
    public final StatT stat;
    
    private RatingT(int id, StatT stat, String name) {
        super(__index++, id, name != null ? name : stat.name);
        this.stat = stat;
    }
    
    public float getCoeff(int playerLevel) {
        return RatingCurve.get(playerLevel, this);
    }
    
    static public final RatingT DODGE = new RatingT(CombatRatingId.DODGE, StatT.DODGE, null);
    static public final RatingT PARRY = new RatingT(CombatRatingId.PARRY, StatT.PARRY, null);
    static public final RatingT BLOCK = new RatingT(CombatRatingId.BLOCK, StatT.BLOCK, null);
    static public final RatingT CRIT = new RatingT(CombatRatingId.CRIT_MELEE, StatT.CRIT, null);    // i dunno if this is correct
    static public final RatingT HASTE = new RatingT(CombatRatingId.HASTE_MELEE, StatT.HASTE, null); // or this  
    static public final RatingT MULTI = new RatingT(CombatRatingId.MULTI, StatT.MULTI, null);
    static public final RatingT MASTERY = new RatingT(CombatRatingId.MASTERY, StatT.MASTERY, null);
    static public final RatingT LEECH = new RatingT(CombatRatingId.LEECH, StatT.LEECH, null);
    static public final RatingT SPEED = new RatingT(CombatRatingId.SPEED, StatT.SPEED, null);
    static public final RatingT AVOID = new RatingT(CombatRatingId.AVOID, StatT.AVOID, null);
    static public final RatingT VERSA_DAMAGE_DONE = new RatingT(CombatRatingId.VERSA_DAMAGE_DONE, StatT.VERSA, "Versatility Damage Done");
    static public final RatingT VERSA_DAMAGE_TAKEN = new RatingT(CombatRatingId.VERSA_DAMAGE_TAKEN, StatT.VERSA, "Versatility Damage Taken");
    //static public final RatingT RESILIENCE_CRIT_TAKEN = new RatingT(CombatRatingId.PVP_RESIL_CRIT_TAKEN, StatT.PVP_RES, "Crit Damage Taken");
    static public final RatingT RESILIENCE_DAMAGE_TAKEN = new RatingT(CombatRatingId.PVP_RESIL_PLAYER_TAKEN, StatT.PVP_RES, "Player Damage Taken");
        
    static public final BitContainer<RatingT> db = new BitContainer<>(RatingT.class);
    static public final BlizzBit<RatingT> blizzBits = new BlizzBit<>(RatingT.class, x -> 1 << x.id - 1);
    
    static public final long MASK_VERSA = db.encode(VERSA_DAMAGE_DONE, VERSA_DAMAGE_TAKEN);
    static public final long MASK_RESIL = db.encode(/*RESILIENCE_CRIT_TAKEN, */RESILIENCE_DAMAGE_TAKEN);

    
    
    /*
     static public final int DODGE = 0;
    static public final int PARRY = 1;
    static public final int BLOCK = 2;
    static public final int CRIT = 3;
    static public final int HASTE = 4;
    static public final int MULTI = 5;
    static public final int MASTERY = 6;
    static public final int LEECH = 7;
    static public final int SPEED = 8;
    static public final int AVOID = 9;
    static public final int READY = 10;
    static public final int VERSA_DAMAGE_DONE = 11;
    static public final int VERSA_DAMAGE_TAKEN = 12;
    static public final int PVP_RESIL_CRIT_TAKEN = 13;
    static public final int RESILIENCE_DAMAGE_TAKEN = 14;
    */
}
