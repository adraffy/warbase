package com.antistupid.warbase.types;

import java.util.ArrayList;
import com.antistupid.warbase.ids.StatId;

abstract public class StatT extends TypeT {
    
    public final String shortName; // shorter,  eg. "Critical Strike" -> "Crit"
    public final String tinyName;  // abbr,     eg. "Health" -> "HP"    
    
    static int _index;    
    private StatT(int id, String name, String shortName, String tinyName) {
        super(_index++, id, name);        
        this.shortName = shortName != null ? shortName : this.name;
        this.tinyName = tinyName != null ? tinyName : this.shortName;
    }
    
    public String formatValue(int value) {
        StringBuilder sb = new StringBuilder(24);
        appendValue(sb, value);
        sb.append(" ");
        sb.append(shortName);
        return sb.toString();
    }
    
    public void appendValue(StringBuilder sb, int value) {
        if (value >= 0) {
            sb.append('+');
        }
        sb.append(value);
    }
        
    static public class Fundamental extends StatT {
        public final ArrayList<StatT> sources = new ArrayList<>();
        Fundamental(int id, String name, String shortName, String tinyName) {
            super(id, name, shortName, tinyName);
        }
    }
    
    static public class Basket extends StatT {
        public final Fundamental[] components;        
        Basket(int id, Fundamental a, Fundamental b) {
            this(id, a.name + "/" + b.name, a.tinyName + "/" + b.tinyName, null, a, b);   
        }
        Basket(int id, String name, String shortName, String tinyName, Fundamental ... stats) {
            super(id, name, shortName, tinyName);
            this.components = stats;
        }
    }
    
    static public final Fundamental MP = new Fundamental(StatId.MANA,             "Mana", null, "MP");
    static public final Fundamental HP = new Fundamental(StatId.HEALTH,             "Health", null, "HP");
    
    static public final Fundamental STA     = new Fundamental(StatId.STAMINA,               "Stamina", null, "Sta");
    static public final Fundamental AGI     = new Fundamental(StatId.AGILITY,               "Agility", null, "Agi");
    static public final Fundamental STR     = new Fundamental(StatId.STRENGTH,              "Strength", null, "Str");
    static public final Fundamental INT     = new Fundamental(StatId.INTELLECT,             "Intellect", null, "Int");
    static public final Fundamental SPI     = new Fundamental(StatId.SPIRIT,                "Spirit", null, "Spi");
    
    static public final Fundamental DODGE   = new Fundamental(StatId.DODGE, "Dodge", null, null);
    static public final Fundamental PARRY   = new Fundamental(StatId.PARRY, "Parry", null, null);
    
    static public final Fundamental PVP_POW = new Fundamental(StatId.PVP_POWER, "PvP Power", null, "PvP");
    static public final Fundamental PVP_RES = new Fundamental(StatId.PVP_RESILIENCE, "PvP Resilience", "PvP Resil", "Resil");

    static public final Fundamental AP   = new Fundamental(StatId.ATTACK_POWER, "Attack Power", "AP", null);
    static public final Fundamental SP   = new Fundamental(StatId.SPELL_POWER, "Spell Power", "SP", null);
    
    static public final Fundamental RESIST_FIRE     = new Fundamental(StatId.RESIST_FIRE,   "Fire Resistance", "Fire Resist", null);
    static public final Fundamental RESIST_ARCANE   = new Fundamental(StatId.RESIST_ARCANE, "Arcane Resistance", "Arcane Resist", null);
    static public final Fundamental RESIST_FROST    = new Fundamental(StatId.RESIST_FROST,  "Frost Resistance", "Frost Resist", null);
    static public final Fundamental RESIST_SHADOW   = new Fundamental(StatId.RESIST_SHADOW, "Shadow Resistance", "Shadow Resist", null);
    static public final Fundamental RESIST_HOLY     = new Fundamental(StatId.RESIST_HOLY,   "Holy Resistance", "Holy Resist", null);
    static public final Fundamental RESIST_NATURE   = new Fundamental(StatId.RESIST_NATURE, "Nature Resistance", "Nature Resist", null);
    
    static public final Fundamental HASTE     = new Fundamental(StatId.HASTE,          "Haste", null, null);
    static public final Fundamental CRIT      = new Fundamental(StatId.CRIT,           "Critical Strike", "Crit", null);
    static public final Fundamental MASTERY   = new Fundamental(StatId.MASTERY,        "Mastery", null, null);
    static public final Fundamental MULTI     = new Fundamental(StatId.MULTISTRIKE,    "Multistrike", "Multi", null);
    static public final Fundamental VERSA     = new Fundamental(StatId.VERSATILITY,    "Versatility", "Versa", null);
    
    static public final Fundamental AVOID     = new Fundamental(StatId.AVOIDANCE,       "Avoidance", "Avoid", null);
    static public final Fundamental LEECH     = new Fundamental(StatId.LEECH,           "Leech", null, null);
    static public final Fundamental ARMOR     = new Fundamental(StatId.BONUS_ARMOR,     "Bonus Armor", "Armor", null);
    static public final Fundamental SPEED     = new Fundamental(StatId.SPEED,           "Movement Speed", "Speed", null);
    static public final Fundamental INDEST    = new Fundamental(StatId.INDESTRUCTIBLE,  "Indestructible", "Indestruct", null);

    static public final Basket AGI_STR = new Basket(StatId.AGI_STR, AGI, STR);
    static public final Basket AGI_INT = new Basket(StatId.AGI_INT, AGI, INT);
    static public final Basket STR_INT = new Basket(StatId.STR_INT, STR, INT);

    static public final Basket STR_AGI_INT = new Basket(StatId.STR_AGI_INT, "Primary", null, null, AGI, STR, INT);
    
    // fake
    static public final Basket ALL_STATS = new Basket(StatId.ALL_STATS, "All Stats", "Stats", null, AGI, STR, INT, STA, SPI);
    static public final Basket ALL_RESIST = new Basket(StatId.ALL_RESIST, "All Resistances", "All Resist", null, RESIST_FIRE, RESIST_ARCANE, RESIST_FROST, RESIST_SHADOW, RESIST_HOLY, RESIST_NATURE);
    static public final Fundamental DAMAGE = new Fundamental(StatId.DAMAGE, "Melee Damage", "Damage", null);
    
    static public final Fundamental SPEED_PERC = new Fundamental(StatId.SPEED_PERC, "Movement Speed", "Speed", null) {
        @Override
        public void appendValue(StringBuilder sb, int value) {
            super.appendValue(sb, value);
            sb.append("%");
        }        
    };
    
    static public final Fundamental SP_FROST    = new Fundamental(StatId.SPELL_POWER_FROST,  "Frost Damage",  null, null);
    static public final Fundamental SP_FIRE     = new Fundamental(StatId.SPELL_POWER_FIRE,   "Fire Damage",   null, null);
    static public final Fundamental SP_SHADOW   = new Fundamental(StatId.SPELL_POWER_SHADOW, "Shadow Damage", null, null);
    static public final Fundamental SP_ARCANE   = new Fundamental(StatId.SPELL_POWER_ARCANE, "Arcane Damage", null, null);
    static public final Fundamental SP_NATURE   = new Fundamental(StatId.SPELL_POWER_NATURE, "Nature Damage", null, null);
    
    //static public final BitContainer<Fundamental> fundamental = new BitContainer<>(Fundamental.class);    
    
    static public final BitContainer<StatT> db = new BitContainer<>(StatT.class);

    static public final long TERT = db.encode(LEECH, AVOID, SPEED, INDEST);
    
    static {
        for (StatT x: db.types) {
            if (x instanceof Fundamental) {
                ((Fundamental)x).sources.add(x);
            } else if (x instanceof Basket) {
                for (Fundamental y: ((Basket)x).components) {
                    y.sources.add(x);
                }                
            }            
        }
    }
    
}
