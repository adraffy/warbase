package warbase.ids;

public class StatId {

    /*
     HP      = new StatT(1,  "HP",   "Health",          null,        Category.RESOURCE,    false),
        MP      = new StatT(2,  "MP",   "Mana",            null,        Category.RESOURCE,    false),
        AGI     = new StatT(3,  "Agi",  "Agility",         null,        Category.PRIMARY,     false),
        STR     = new StatT(4,  "Str",  "Strength",        null,        Category.PRIMARY,     false),
        INT     = new StatT(5,  "Int",  "Intellect",       null,        Category.PRIMARY,     false),
        SPI     = new StatT(6,  "Spi",  "Spirit",          null,        Category.PRIMARY,     true),
        STA     = new StatT(7,  "Stam", "Stamina",         null,        Category.PRIMARY,     false),
        DODGE   = new StatT(13, null,   "Dodge",           null,        Category.SECONDARY,   true),
        PARRY   = new StatT(14, null,   "Parry",           null,        Category.SECONDARY,   true),
        HIT     = new StatT(31, null,   "Hit",             null,        Category.SECONDARY,   true),
        CRIT    = new StatT(32, "Crit", "Critical Strike", "%+d Crit",  Category.SECONDARY,   true),
        PVP_RES = new StatT(35, "PvP Resil",   "PvP Resilience",  null,        Category.PVP,         false),
        HASTE   = new StatT(36, null,   "Haste",           null,        Category.SECONDARY,   true),
        EXP     = new StatT(37, null,   "Expertise",       null,        Category.SECONDARY,   true),
        AP      = new StatT(38, "AP",   "Attack Power",    "%+d AP",    Category.EXTRA,       false),
        SP      = new StatT(45, "SP",   "Spell Power",     "%+d SP",    Category.EXTRA,       false),
        MASTERY = new StatT(49, null,   "Mastery",         null,        Category.SECONDARY,   true),
        ARMOR   = new StatT(50, null,   "Extra Armor",     null,        Category.EXTRA,       false),
        PVP_POW = new StatT(57, null,   "PvP Power",       null,        Category.PVP,         false),
        WDMG    = new StatT(100, null,  "Weapon Damage",   null,        Category.CUSTOM,      false),
        SPEED   = new StatT(101, null,  "Movement Speed",  "%+d%% Speed",   Category.CUSTOM,      false),
    */
    
    static public final int INVALID = -1;
    
    static public final int MANA = 0; // uh
    static public final int HEALTH = 1;
    static public final int AGILITY = 3;
    static public final int STRENGTH = 4;
    static public final int INTELLECT = 5;
    static public final int SPIRIT = 6;
    static public final int STAMINA = 7;
    static public final int DODGE = 13;
    static public final int PARRY = 14;
    static public final int BLOCK = 15;
    
    static public final int CRIT_MELEE = 19; // old
    static public final int CRIT_RANGE = 20; // old
    static public final int CRIT_SPELL = 21; // old
    static public final int HASTE_MELEE = 28; // old
    static public final int HASTE_RANGE = 29; // old
    static public final int HASTE_SPELL = 30; // old
    
    static public final int HIT = 31;
    static public final int CRIT = 32;
    static public final int PVP_RESILIENCE = 35;
    static public final int HASTE = 36;
    static public final int EXPERTISE = 37;
    static public final int ATTACK_POWER = 38;
    static public final int VERSATILITY = 40;
    static public final int MANA_PER_5 = 43;
    static public final int SPELL_POWER = 45;
    static public final int MASTERY = 49;
    
    static public final int BONUS_ARMOR     = 50;
    /*
    ITEM_MOD_FIRE_RESISTANCE          = 51,
  ITEM_MOD_FROST_RESISTANCE         = 52,
  ITEM_MOD_HOLY_RESISTANCE          = 53,
  ITEM_MOD_SHADOW_RESISTANCE        = 54,
  ITEM_MOD_NATURE_RESISTANCE        = 55,
  ITEM_MOD_ARCANE_RESISTANCE        = 56,
    */
    static public final int RESIST_FIRE     = 51;
    static public final int RESIST_FROST    = 52;
    static public final int RESIST_HOLY     = 53;
    static public final int RESIST_SHADOW   = 54;
    static public final int RESIST_NATURE   = 55;
    static public final int RESIST_ARCANE   = 56;
    static public final int PVP_POWER       = 57;    
    static public final int AMPLIFY         = 58;
    static public final int MULTISTRIKE     = 59;
    static public final int READINESS       = 60;
    static public final int SPEED           = 61;
    static public final int LEECH           = 62;
    static public final int AVOIDANCE       = 63;    
    static public final int INDESTRUCTIBLE  = 64;
    static public final int CLEAVE          = 66;    
    static public final int STR_AGI_INT     = 71;
    static public final int AGI_STR         = 72; 
    static public final int AGI_INT         = 73;
    static public final int STR_INT         = 74;
    
         
    static public final int ALL_STATS       = 75; // fake
    static public final int ALL_RESIST      = 76;
    static public final int DAMAGE          = 77;
    static public final int SPEED_PERC      = 78;
    
    static public final int SPELL_POWER_FIRE    = 79;
    static public final int SPELL_POWER_NATURE  = 80;
    static public final int SPELL_POWER_SHADOW  = 81;
    static public final int SPELL_POWER_ARCANE  = 82;
    static public final int SPELL_POWER_FROST   = 83;
    
    static public int convert(int id) {
        switch (id) {
            case CRIT_MELEE:
            case CRIT_RANGE:
            case CRIT_SPELL:
                return CRIT;    
            case HASTE_MELEE:
            case HASTE_SPELL:
            case HASTE_RANGE:
                return HASTE;
            default: 
                return id;
        }
    }
    
    static public boolean isDead(int id) {
        switch (id) {            
            //case PVP_POWER: 
            //case PVP_RESILIENCE:
            case BLOCK:
            case MANA_PER_5:
            /*case RESIST_FIRE:
            case RESIST_FROST:
            case RESIST_HOLY:
            case RESIST_SHADOW:
            case RESIST_NATURE:
            case RESIST_ARCANE:*/
                return true;
            default:
                return false;
        }
    }
    
    /*
  ITEM_MOD_NONE                     = -1,
  ITEM_MOD_MANA                     = 0,
  ITEM_MOD_HEALTH                   = 1,
  ITEM_MOD_AGILITY                  = 3,
  ITEM_MOD_STRENGTH                 = 4,
  ITEM_MOD_INTELLECT                = 5,
  ITEM_MOD_SPIRIT                   = 6,
  ITEM_MOD_STAMINA                  = 7,
  ITEM_MOD_DEFENSE_SKILL_RATING     = 12,
  ITEM_MOD_DODGE_RATING             = 13,
  ITEM_MOD_PARRY_RATING             = 14,
  ITEM_MOD_BLOCK_RATING             = 15,
  ITEM_MOD_HIT_MELEE_RATING         = 16,
  ITEM_MOD_HIT_RANGED_RATING        = 17,
  ITEM_MOD_HIT_SPELL_RATING         = 18,
  ITEM_MOD_CRIT_MELEE_RATING        = 19,
  ITEM_MOD_CRIT_RANGED_RATING       = 20,
  ITEM_MOD_CRIT_SPELL_RATING        = 21,
  ITEM_MOD_HIT_TAKEN_MELEE_RATING   = 22,
  ITEM_MOD_HIT_TAKEN_RANGED_RATING  = 23,
  ITEM_MOD_HIT_TAKEN_SPELL_RATING   = 24,
  ITEM_MOD_CRIT_TAKEN_MELEE_RATING  = 25,
  ITEM_MOD_CRIT_TAKEN_RANGED_RATING = 26,
  ITEM_MOD_CRIT_TAKEN_SPELL_RATING  = 27,
  ITEM_MOD_HASTE_MELEE_RATING       = 28,
  ITEM_MOD_HASTE_RANGED_RATING      = 29,
  ITEM_MOD_HASTE_SPELL_RATING       = 30,
  ITEM_MOD_HIT_RATING               = 31,
  ITEM_MOD_CRIT_RATING              = 32,
  ITEM_MOD_HIT_TAKEN_RATING         = 33,
  ITEM_MOD_CRIT_TAKEN_RATING        = 34,
  ITEM_MOD_RESILIENCE_RATING        = 35,
  ITEM_MOD_HASTE_RATING             = 36,
  ITEM_MOD_EXPERTISE_RATING         = 37,
  ITEM_MOD_ATTACK_POWER             = 38,
  ITEM_MOD_RANGED_ATTACK_POWER      = 39,
  ITEM_MOD_VERSATILITY_RATING       = 40,
  ITEM_MOD_SPELL_HEALING_DONE       = 41,                 // deprecated
  ITEM_MOD_SPELL_DAMAGE_DONE        = 42,                 // deprecated
  ITEM_MOD_MANA_REGENERATION        = 43,
  ITEM_MOD_ARMOR_PENETRATION_RATING = 44,
  ITEM_MOD_SPELL_POWER              = 45,
  ITEM_MOD_HEALTH_REGEN             = 46,
  ITEM_MOD_SPELL_PENETRATION        = 47,
  ITEM_MOD_BLOCK_VALUE              = 48,
  ITEM_MOD_MASTERY_RATING           = 49,
  ITEM_MOD_EXTRA_ARMOR              = 50,
  ITEM_MOD_FIRE_RESISTANCE          = 51,
    */
    
}
