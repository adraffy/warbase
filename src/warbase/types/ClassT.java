package warbase.types;

import java.util.ArrayList;
import java.util.function.Consumer;


public class ClassT extends TypeT {

    public final String shortName;
    public final String iconName;
    
    final long allowedRaces;
    final long allowedWeapons;
    final long allowedArmors;
    
    public final ArrayList<SpecT> specs = new ArrayList<>(4);
    public final ArrayList<RaceT> races = new ArrayList<>(RaceT.db.size());
        
    static private int _index;
    private ClassT(int id, String name, String shortName, String iconName, long allowedRaces, long allowedWeapons, long allowedArmors) {
        super(_index++, id, name);
        this.shortName = shortName != null ? shortName : name;
        this.iconName = iconName;
        this.allowedRaces = allowedRaces;
        this.allowedWeapons = allowedWeapons | WeaponT.TRIVIAL;
        this.allowedArmors = allowedArmors | ArmorT.TRIVIAL;
    }
    
    public boolean canBe(RaceT race) {
        return checkBit(allowedRaces, race);
    }
    
    public boolean canWield(WeaponT weapon) {
        return checkBit(allowedWeapons, weapon);
    }
    
    public boolean canWear(int playerLevel, ArmorT armor) {
        return checkBit(allowedArmors, armor);
    }
    
    @Override
    void registerNames(Consumer<String> c) {
        c.accept(shortName);
    }
    
    static class MailAt40 extends ClassT {
        public MailAt40(int id, String name, String shortName, String iconName, long allowedRaces, long allowedWeapons, long allowedArmors) {
            super(id, name, shortName, iconName, allowedRaces, allowedWeapons, allowedArmors);
        }
        @Override
        public boolean canWear(int playerLevel, ArmorT armor) {
            return super.canWear(playerLevel, armor) | (playerLevel >= 40 && armor == ArmorT.MAIL);
        }        
    }    

    static public final ClassT WARRIOR = new ClassT(
            1, "Warrior", "War", "warrior",
            RaceT.db.encode(
                    RaceT.BE, 
                    RaceT.DRAENEI, 
                    RaceT.DWARF, 
                    RaceT.GNOME, 
                    RaceT.GOBLIN, 
                    RaceT.HUMAN, 
                    RaceT.NE, 
                    RaceT.ORC,
                    RaceT.PANDAREN_A, 
                    RaceT.PANDAREN_H, 
                    RaceT.PANDAREN_N,  
                    RaceT.TAUREN, 
                    RaceT.TROLL, 
                    RaceT.UNDEAD, 
                    RaceT.WORGEN
            ),
            WeaponT.db.encode(
                    WeaponT.DAGGER,
                    WeaponT.FIST,
                    WeaponT.ONE_HAND_AXE,
                    WeaponT.ONE_HAND_MACE,
                    WeaponT.ONE_HAND_SWORD,
                    WeaponT.TWO_HAND_AXE,
                    WeaponT.TWO_HAND_MACE,
                    WeaponT.TWO_HAND_SWORD,
                    WeaponT.POLEARM,
                    WeaponT.STAFF,
                    WeaponT.GUN,
                    WeaponT.BOW,
                    WeaponT.XBOW
            ),
            ArmorT.db.encode(
                    ArmorT.CLOTH,
                    ArmorT.LEATHER,
                    ArmorT.MAIL,
                    ArmorT.PLATE,
                    ArmorT.SHIELD
            )
    );
    static public final ClassT PALADIN = new ClassT(
            2, "Paladin", "Pally", "paladin",
            RaceT.db.encode(
                    RaceT.BE, 
                    RaceT.DRAENEI, 
                    RaceT.DWARF, 
                    RaceT.HUMAN, 
                    RaceT.TAUREN
            ),
            WeaponT.db.encode(
                    WeaponT.ONE_HAND_AXE,
                    WeaponT.ONE_HAND_MACE,
                    WeaponT.ONE_HAND_SWORD,
                    WeaponT.TWO_HAND_AXE,
                    WeaponT.TWO_HAND_MACE,
                    WeaponT.TWO_HAND_SWORD,
                    WeaponT.POLEARM
            ),
            ArmorT.db.encode(
                    ArmorT.CLOTH,
                    ArmorT.LEATHER,
                    ArmorT.MAIL,
                    ArmorT.PLATE,
                    ArmorT.SHIELD
            )
    );
    static public final ClassT HUNTER = new MailAt40(
            3, "Hunter", "Hunt", "hunter",
            RaceT.db.encode(
                    RaceT.BE, 
                    RaceT.DRAENEI,
                    RaceT.DWARF, 
                    RaceT.GOBLIN, 
                    RaceT.HUMAN, 
                    RaceT.NE, 
                    RaceT.ORC,
                    RaceT.PANDAREN_A, 
                    RaceT.PANDAREN_H, 
                    RaceT.PANDAREN_N,  
                    RaceT.TAUREN, 
                    RaceT.TROLL, 
                    RaceT.UNDEAD, 
                    RaceT.WORGEN
            ),
            WeaponT.db.encode(
                    WeaponT.DAGGER,
                    WeaponT.FIST,
                    WeaponT.ONE_HAND_AXE,
                    WeaponT.ONE_HAND_SWORD,
                    WeaponT.TWO_HAND_AXE,
                    WeaponT.TWO_HAND_SWORD,
                    WeaponT.POLEARM,
                    WeaponT.STAFF,
                    WeaponT.GUN,
                    WeaponT.BOW,
                    WeaponT.XBOW
            ),
            ArmorT.db.encode(
                    ArmorT.CLOTH,
                    ArmorT.LEATHER
            )
    );
    static public final ClassT ROGUE = new ClassT(
            4, "Rogue", null, "rogue",        
            RaceT.db.encode(
                    RaceT.BE, 
                    RaceT.DWARF, 
                    RaceT.GNOME, 
                    RaceT.GOBLIN, 
                    RaceT.HUMAN, 
                    RaceT.NE, 
                    RaceT.ORC,
                    RaceT.PANDAREN_A, 
                    RaceT.PANDAREN_H, 
                    RaceT.PANDAREN_N,  
                    RaceT.TROLL, 
                    RaceT.UNDEAD, 
                    RaceT.WORGEN),
            WeaponT.db.encode(
                    WeaponT.DAGGER,
                    WeaponT.FIST,
                    WeaponT.ONE_HAND_AXE,
                    WeaponT.ONE_HAND_MACE,
                    WeaponT.ONE_HAND_SWORD,
                    WeaponT.GUN,
                    WeaponT.BOW,
                    WeaponT.XBOW
            ),
            ArmorT.db.encode(
                    ArmorT.CLOTH,
                    ArmorT.LEATHER
            )
    );
    static public final ClassT PRIEST = new ClassT(
            5, "Priest", null, "priest", 
            RaceT.db.encode(
                    RaceT.BE, 
                    RaceT.DRAENEI, 
                    RaceT.DWARF, 
                    RaceT.GNOME, 
                    RaceT.GOBLIN, 
                    RaceT.HUMAN, 
                    RaceT.NE, 
                    RaceT.PANDAREN_A, 
                    RaceT.PANDAREN_H, 
                    RaceT.PANDAREN_N,  
                    RaceT.TAUREN, 
                    RaceT.TROLL, 
                    RaceT.UNDEAD, 
                    RaceT.WORGEN
            ), 
            WeaponT.db.encode(
                    WeaponT.DAGGER, 
                    WeaponT.ONE_HAND_MACE, 
                    WeaponT.STAFF, 
                    WeaponT.WAND
            ),
            ArmorT.db.encode(
                    ArmorT.CLOTH
            )        
    );
    static public final ClassT DK  = new ClassT(
            6, "Death Knight", "DK", "deathknight",
            RaceT.db.encode(
                    RaceT.BE,
                    RaceT.DRAENEI,
                    RaceT.DWARF,
                    RaceT.GNOME,
                    RaceT.GOBLIN,
                    RaceT.HUMAN,
                    RaceT.NE,
                    RaceT.ORC,
                    RaceT.TAUREN,
                    RaceT.TROLL,
                    RaceT.UNDEAD,
                    RaceT.WORGEN
            ), 
            WeaponT.db.encode(
                    WeaponT.ONE_HAND_AXE,
                    WeaponT.ONE_HAND_MACE,
                    WeaponT.ONE_HAND_SWORD,
                    WeaponT.POLEARM,
                    WeaponT.TWO_HAND_AXE,
                    WeaponT.TWO_HAND_MACE,
                    WeaponT.TWO_HAND_SWORD
            ),
            ArmorT.db.encode(
                    ArmorT.CLOTH,
                    ArmorT.LEATHER,
                    ArmorT.MAIL,
                    ArmorT.PLATE
            )
    );
    static public final ClassT SHAMAN = new MailAt40(
            7, "Shaman", "Sham", "shaman",
            RaceT.db.encode(
                    RaceT.DRAENEI,
                    RaceT.DWARF,
                    RaceT.GOBLIN,
                    RaceT.ORC,
                    RaceT.PANDAREN_A,
                    RaceT.PANDAREN_H,
                    RaceT.PANDAREN_N,
                    RaceT.TAUREN,
                    RaceT.TROLL), 
            WeaponT.db.encode(                    
                    WeaponT.ONE_HAND_MACE,
                    WeaponT.ONE_HAND_AXE,
                    WeaponT.TWO_HAND_MACE,
                    WeaponT.TWO_HAND_AXE,
                    WeaponT.STAFF,
                    WeaponT.DAGGER,
                    WeaponT.FIST),
            ArmorT.db.encode(
                    ArmorT.CLOTH,
                    ArmorT.LEATHER,
                    ArmorT.SHIELD)
    );           
    static public final ClassT MAGE = new ClassT(
            8, "Mage", null, "mage",
            RaceT.db.encode(
                    RaceT.BE,
                    RaceT.DRAENEI,
                    RaceT.DWARF,
                    RaceT.GNOME,
                    RaceT.GOBLIN,
                    RaceT.HUMAN,
                    RaceT.NE,
                    RaceT.ORC,
                    RaceT.PANDAREN_A,
                    RaceT.PANDAREN_H,
                    RaceT.PANDAREN_N,
                    RaceT.TROLL,
                    RaceT.UNDEAD,
                    RaceT.WORGEN
            ), 
            WeaponT.db.encode(
                    WeaponT.DAGGER,
                    WeaponT.ONE_HAND_SWORD,
                    WeaponT.STAFF,
                    WeaponT.WAND
            ),
            ArmorT.db.encode(
                    ArmorT.CLOTH
            )    
    );
    static public final ClassT WARLOCK = new ClassT(
            9, "Warlock", "Lock", "warlock",
            RaceT.db.encode(
                    RaceT.BE,
                    RaceT.DWARF,
                    RaceT.GNOME,
                    RaceT.GOBLIN,
                    RaceT.HUMAN,
                    RaceT.ORC,
                    RaceT.TROLL,
                    RaceT.UNDEAD,
                    RaceT.WORGEN
            ), 
            WeaponT.db.encode(
                    WeaponT.DAGGER,
                    WeaponT.ONE_HAND_SWORD,
                    WeaponT.STAFF,
                    WeaponT.WAND
            ),
            ArmorT.db.encode(
                    ArmorT.CLOTH
            )
    );
    static public final ClassT MONK = new ClassT(
            10, "Monk", null, "monk",
            RaceT.db.encode(
                    RaceT.BE, 
                    RaceT.DRAENEI, 
                    RaceT.DWARF, 
                    RaceT.GNOME, 
                    RaceT.HUMAN, 
                    RaceT.NE, 
                    RaceT.ORC, 
                    RaceT.PANDAREN_A, 
                    RaceT.PANDAREN_H, 
                    RaceT.PANDAREN_N, 
                    RaceT.TAUREN, 
                    RaceT.TROLL, 
                    RaceT.UNDEAD
            ),
            WeaponT.db.encode(
                    WeaponT.ONE_HAND_AXE, 
                    WeaponT.ONE_HAND_MACE, 
                    WeaponT.ONE_HAND_SWORD, 
                    WeaponT.FIST,
                    WeaponT.STAFF, 
                    WeaponT.POLEARM
            ),
            ArmorT.db.encode(
                    ArmorT.CLOTH,
                    ArmorT.LEATHER
            )
    );    
    static public final ClassT DRUID = new ClassT(
            11, "Druid", null, "druid",
            RaceT.db.encode(
                    RaceT.WORGEN,
                    RaceT.NE,
                    RaceT.TAUREN,
                    RaceT.TROLL
            ),
            WeaponT.db.encode(            
                    WeaponT.DAGGER, 
                    WeaponT.ONE_HAND_MACE, 
                    WeaponT.TWO_HAND_MACE, 
                    WeaponT.POLEARM, 
                    WeaponT.STAFF, 
                    WeaponT.FIST
            ),           
            ArmorT.db.encode(
                    ArmorT.CLOTH,
                    ArmorT.LEATHER
            )
    );

    // ---
    
    static public final BitContainer<ClassT> db = new BitContainer<>(ClassT.class); 
    static public final TypeNames<ClassT> names = new TypeNames<>(db.types); 
    
    static public final BlizzBit<ClassT> blizzBits = new BlizzBit<>(ClassT.class, x -> 1 << (x.id - 1));
 
    static {
        for (ClassT x: ClassT.db.types) {            
            RaceT.db.forEach(x.allowedRaces, r -> x.races.add(r));
        }
    }
    
    
}
