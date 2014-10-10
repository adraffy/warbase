package com.antistupid.warbase.types;

import java.util.ArrayList;
import java.util.function.Consumer;

public class RaceT extends TypeT {

    public final FactionRoot faction;
    
    static private int _index;
    public RaceT(int id, String name, FactionRoot faction) {
        super(_index++, id, name);
        this.faction = faction;
    }
        
    public ArrayList<ClassT> getClasses() {
        return ClassT.db.select(x -> memberOf(x.allowedRaces));
    }
    
    public ArrayList<SpecT> getSpecs() {
        return SpecT.db.select(x -> memberOf(x.classType.allowedRaces));
    }

    // ---
    
    static public final RaceT HUMAN        = new RaceT(1,  "Human",        FactionRoot.ALLIANCE);
    static public final RaceT DWARF        = new RaceT(3,  "Dwarf",        FactionRoot.ALLIANCE);
    static public final RaceT GNOME        = new RaceT(7,  "Gnome",        FactionRoot.ALLIANCE);
    static public final RaceT NE           = new RaceT(4,  "Night Elf",    FactionRoot.ALLIANCE) {
        @Override
        void registerNames(Consumer<String> c) {
            c.accept("NE");
            c.accept("Nelf");
        }
    };
    static public final RaceT DRAENEI      = new RaceT(11, "Draenei",      FactionRoot.ALLIANCE);
    static public final RaceT WORGEN       = new RaceT(22, "Worgen",       FactionRoot.ALLIANCE);
    static public final RaceT PANDAREN_A   = new RaceT(25, "Pandaren (A)", FactionRoot.ALLIANCE);

    // ---
    
    static public final RaceT ORC          = new RaceT(2,  "Orc",          FactionRoot.HORDE);
    static public final RaceT UNDEAD       = new RaceT(5,  "Undead",       FactionRoot.HORDE);
    static public final RaceT TAUREN       = new RaceT(6,  "Tauren",       FactionRoot.HORDE);
    static public final RaceT TROLL        = new RaceT(8,  "Troll",        FactionRoot.HORDE);
    static public final RaceT GOBLIN       = new RaceT(9,  "Goblin",       FactionRoot.HORDE);
    static public final RaceT BE           = new RaceT(10, "Blood Elf",    FactionRoot.HORDE) {
        @Override
        void registerNames(Consumer<String> c) {
            c.accept("BE");
            c.accept("Belf");
        }
    };
    static public final RaceT PANDAREN_H   = new RaceT(26, "Pandaren (H)", FactionRoot.HORDE);
    
    // ---

    static public final RaceT PANDAREN_N   = new RaceT(24, "Pandaren (N)", FactionRoot.NEUTRAL);

    static public final BitContainer<RaceT> db = new BitContainer<>(RaceT.class);    
    static public final BlizzBit<RaceT> blizzBits = new BlizzBit<>(RaceT.class, x -> 1 << (x.id - 1));
    static public final TypeNames<RaceT> names = new TypeNames<>(db.types);
    
    static public final long ALLIANCE = db.encode(HUMAN, DWARF, GNOME, NE, DRAENEI, WORGEN, PANDAREN_A);
    static public final long HORDE = db.encode(ORC, UNDEAD, TAUREN, TROLL, GOBLIN, BE, PANDAREN_H);
    
    
}
