package com.antistupid.warbase.types;

import com.antistupid.warbase.data.PlayerScaling;
import java.util.ArrayList;
import java.util.function.Consumer;

public class RaceT extends TypeT {

    public final FactionRoot faction;    
    public final long compactBaseStats;
    public final String iconNameMale;
    public final String iconNameFemale;
    
    static private int _index;
    public RaceT(int id, String name, String iconNameMale, String iconNameFemale, FactionRoot faction, long baseStats) {
        super(_index++, id, name);
        this.iconNameMale = iconNameMale;
        this.iconNameFemale = iconNameFemale;
        this.faction = faction;
        this.compactBaseStats = baseStats;        
    }
        
    public ArrayList<ClassT> getClasses() {
        return ClassT.db.selectList(x -> isMemberOf(x.allowedRaces));
    }
    
    public ArrayList<SpecT> getSpecs() {
        return SpecT.db.selectList(x -> isMemberOf(x.classType.allowedRaces));
    }    
    
    static public final RaceT HUMAN        = new RaceT(1,  "Human",        "race_human_male",       "race_human_female",    FactionRoot.ALLIANCE, 0L);
    static public final RaceT DWARF        = new RaceT(3,  "Dwarf",        "race_dwarf_male",       "race_dwarf_female",    FactionRoot.ALLIANCE, 0xffeffeffb005001L);
    static public final RaceT GNOME        = new RaceT(7,  "Gnome",        "race_gnome_male",       "race_gnome_female",    FactionRoot.ALLIANCE, 0x3002ffa000L);
    static public final RaceT NE           = new RaceT(4,  "Night Elf",    "race_nightelf_male",    "race_nightelf_female", FactionRoot.ALLIANCE, 0x4ffb000L) {
        @Override
        void registerNames(Consumer<String> c) {
            c.accept("Nelf");
        }
    };
    static public final RaceT DRAENEI      = new RaceT(11, "Draenei",      "race_draenei_male",     "race_draenei_female",  FactionRoot.ALLIANCE, 0x2000ffc001000L);
    static public final RaceT WORGEN       = new RaceT(22, "Worgen",       "race_worgen_male",      "race_worgen_female",   FactionRoot.ALLIANCE, 0xffeffb002003000L);
    static public final RaceT PANDAREN_A   = new RaceT(25, "Pandaren (A)", "race_pandaren_male",    "race_pandaren_female", FactionRoot.ALLIANCE, 0x2ffeffd000001L);

    // ---
    
    static public final RaceT ORC          = new RaceT(2,  "Orc",          "race_orc_male",         "race_orc_female",      FactionRoot.HORDE, 0x2ffcffc003001L);
    static public final RaceT UNDEAD       = new RaceT(5,  "Undead",       "race_scourge_male",     "race_scourge_female",  FactionRoot.HORDE, 0x5ffdffdffe000L);
    static public final RaceT TAUREN       = new RaceT(6,  "Tauren",       "race_tauren_male",      "race_tauren_female",   FactionRoot.HORDE, 0x2ffbffb005001L);
    static public final RaceT TROLL        = new RaceT(8,  "Troll",        "race_troll_male",       "race_troll_female",    FactionRoot.HORDE, 0x1ffb002001000L);
    static public final RaceT GOBLIN       = new RaceT(9,  "Goblin",       "race_goblin_male",      "race_goblin_female",   FactionRoot.HORDE, 0xffd003002ffc000L);
    static public final RaceT BE           = new RaceT(10, "Blood Elf",    "race_bloodelf_male",    "race_bloodelf_female", FactionRoot.HORDE, 0xffd003002ffc000L) {
        @Override
        void registerNames(Consumer<String> c) {
            c.accept("Belf");
        }
    };
    static public final RaceT PANDAREN_H   = new RaceT(26, "Pandaren (H)", "race_pandaren_male",    "race_pandaren_female", FactionRoot.HORDE, 0x2ffeffd000001L);
    
    // ---

    static public final RaceT PANDAREN_N   = new RaceT(24, "Pandaren (N)", "race_pandaren_male",    "race_pandaren_female", FactionRoot.NEUTRAL, 0x2ffeffd000001L);

    // ---
    
    static public final BitContainer<RaceT> db = new BitContainer<>(RaceT.class);    
    static public final BlizzBit<RaceT> blizzBits = new BlizzBit<>(RaceT.class, x -> 1 << (x.id - 1));
    static public final TypeNames<RaceT> names = new TypeNames<>(db.types);
    
    // ---
    
    static public final long MASK_ALLIANCE = db.encode(HUMAN, DWARF, GNOME, NE, DRAENEI, WORGEN, PANDAREN_A);
    static public final long MASK_HORDE = db.encode(ORC, UNDEAD, TAUREN, TROLL, GOBLIN, BE, PANDAREN_H);
  
    // ---
    
    static public final long MASK_PANDAREN = db.encode(PANDAREN_A, PANDAREN_H, PANDAREN_N);
    
    static public boolean isPandaren(RaceT race) {
        return checkBit(MASK_PANDAREN, race);
        //return race == PANDAREN_A || race == PANDAREN_H || race == PANDAREN_N;
    }
    
    static public RaceT resolvePandarenFaction(long bits) {        
        boolean a = PANDAREN_A.isMemberOf(bits);
        boolean h = PANDAREN_H.isMemberOf(bits);
        boolean n = PANDAREN_N.isMemberOf(bits);
        if (a && !h && !n) {
            return PANDAREN_A;
        } else if (!a && h && !n) {
            return PANDAREN_H;
        } else if (!a && !h && n) {
            return PANDAREN_N; // dont think this happens
        } else {
            return null;
        }        
    }
    
    // agi/int/str
    //static public final int DRAENEI_HEROIC_PRESENCE_SCALING_ID = 1; // 6562
    //static public final double DRAENEI_HEROIC_PRESENCE_SCALING_COEFF = 0.25;    
    static public int getDraenei_heroicPresence_agiIntStr(int playerLevel) {
        return PlayerScaling.getInt(playerLevel, 1, 0.25);
    }
        
    // versa
    //static public final int HUMAN_THE_HUMAN_SPIRIT_SCALING_ID = 1; //20598
    //static public final double HUMAN_THE_HUMAN_SPIRIT_SCALING_COEFF = 0.385;
    static public int getHuman_theHumanSpirit_versa(int playerLevel) {
        return PlayerScaling.getInt(playerLevel, 1, 0.385);
    }
    
    static public int getTauren_endurance_sta(int playerLevel) { // 20550
        return PlayerScaling.getInt(playerLevel, 1, 0.755); 
    }
    
    static public int getOrc_bloodFury_ap(int playerLevel) { // 20572
        return PlayerScaling.getInt(playerLevel, 1, 1.32); 
    }
    
}
