package com.antistupid.warbase.types;

import java.util.ArrayList;
import java.util.function.Consumer;

public class RaceT extends TypeT {

    public final FactionRoot faction;    
    public final long compactBaseStats;
    
    static private int _index;
    public RaceT(int id, String name, FactionRoot faction, long baseStats) {
        super(_index++, id, name);
        this.faction = faction;
        this.compactBaseStats = baseStats;        
    }
        
    public ArrayList<ClassT> getClasses() {
        return ClassT.db.select(x -> memberOf(x.allowedRaces));
    }
    
    public ArrayList<SpecT> getSpecs() {
        return SpecT.db.select(x -> memberOf(x.classType.allowedRaces));
    }    
    
    static public final RaceT HUMAN        = new RaceT(1,  "Human",        FactionRoot.ALLIANCE, 0L);
    static public final RaceT DWARF        = new RaceT(3,  "Dwarf",        FactionRoot.ALLIANCE, 0xffeffeffb005001L);
    static public final RaceT GNOME        = new RaceT(7,  "Gnome",        FactionRoot.ALLIANCE, 0x3002ffa000L);
    static public final RaceT NE           = new RaceT(4,  "Night Elf",    FactionRoot.ALLIANCE, 0x4ffb000L) {
        @Override
        void registerNames(Consumer<String> c) {
            c.accept("NE");
            c.accept("Nelf");
        }
    };
    static public final RaceT DRAENEI      = new RaceT(11, "Draenei",      FactionRoot.ALLIANCE, 0x2000ffc001000L);
    static public final RaceT WORGEN       = new RaceT(22, "Worgen",       FactionRoot.ALLIANCE, 0xffeffb002003000L);
    static public final RaceT PANDAREN_A   = new RaceT(25, "Pandaren (A)", FactionRoot.ALLIANCE, 0x2ffeffd000001L);

    // ---
    
    static public final RaceT ORC          = new RaceT(2,  "Orc",          FactionRoot.HORDE, 0x2ffcffc003001L);
    static public final RaceT UNDEAD       = new RaceT(5,  "Undead",       FactionRoot.HORDE, 0x5ffdffdffe000L);
    static public final RaceT TAUREN       = new RaceT(6,  "Tauren",       FactionRoot.HORDE, 0x2ffbffb005001L);
    static public final RaceT TROLL        = new RaceT(8,  "Troll",        FactionRoot.HORDE, 0x1ffb002001000L);
    static public final RaceT GOBLIN       = new RaceT(9,  "Goblin",       FactionRoot.HORDE, 0xffd003002ffc000L);
    static public final RaceT BE           = new RaceT(10, "Blood Elf",    FactionRoot.HORDE, 0xffd003002ffc000L) {
        @Override
        void registerNames(Consumer<String> c) {
            c.accept("BE");
            c.accept("Belf");
        }
    };
    static public final RaceT PANDAREN_H   = new RaceT(26, "Pandaren (H)", FactionRoot.HORDE, 0x2ffeffd000001L);
    
    // ---

    static public final RaceT PANDAREN_N   = new RaceT(24, "Pandaren (N)", FactionRoot.NEUTRAL, 0x2ffeffd000001L);

    static public final BitContainer<RaceT> db = new BitContainer<>(RaceT.class);    
    static public final BlizzBit<RaceT> blizzBits = new BlizzBit<>(RaceT.class, x -> 1 << (x.id - 1));
    static public final TypeNames<RaceT> names = new TypeNames<>(db.types);
    
    static public final long ALLIANCE = db.encode(HUMAN, DWARF, GNOME, NE, DRAENEI, WORGEN, PANDAREN_A);
    static public final long HORDE = db.encode(ORC, UNDEAD, TAUREN, TROLL, GOBLIN, BE, PANDAREN_H);
    
    
}
