package warbase.ids;

public class SpellEffectType {
    
    static public final int NONE = 0;
    static public final int DUMMY = 3;
    static public final int APPLY_AURA = 6;
    static public final int ENCHANT_ITEM = 53;
    static public final int WEAPON_PERCENT_DAMAGE = 31;
    static public final int CHANGE_ITEM_BONUS = 223;

    static public class SubType {        
        static public final int NONE = 0;
        static public final int DUMMY = 4;
        static public final int MOD_THREAT = 10;
        static public final int MOD_DAMAGE_DONE = 13;
        static public final int MOD_RESIST = 22;
        static public final int MOD_STAT = 29;        
        static public final int MOD_SKILL = 30; // misc_value = profid, base = amount
        static public final int MOD_INCREASE_HEALTH = 34;
        static public final int MOD_INCREASE_SWIM_SPEED = 58;
        static public final int MOD_DAMAGE_DONE_CREATURE = 59;
        static public final int MOD_SPEED_ALWAYS = 129;
    }
 
    
    
}
