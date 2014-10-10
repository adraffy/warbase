package com.antistupid.warbase.types;

import com.antistupid.warbase.stats.CompactBaseStats;
import com.antistupid.warbase.stats.StatMap;
import java.util.ArrayList;
import java.util.function.Consumer;


public class ClassT extends TypeT {

    public final String shortName;
    public final String iconName;
    
    final long allowedRaces;
    final long allowedWeapons;
    final long allowedArmors;
    final long[] baseStats;
    
    public final ArrayList<SpecT> specs = new ArrayList<>(4);
    public final ArrayList<RaceT> races = new ArrayList<>(RaceT.db.size());
    
    static private int _index;
    private ClassT(int id, String name, String shortName, String iconName, long allowedRaces, long allowedWeapons, long allowedArmors, long[] baseStats) {
        super(_index++, id, name);
        this.shortName = shortName != null ? shortName : name;
        this.iconName = iconName;
        this.allowedRaces = allowedRaces;
        this.allowedWeapons = allowedWeapons | WeaponT.TRIVIAL;
        this.allowedArmors = allowedArmors | ArmorT.TRIVIAL;
        this.baseStats = baseStats;
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
    
    /*
    public int getBaseStat(StatT stat, int playerLevel) {
        return CompactBaseStats.getStat(stat, getCompactBaseStats(playerLevel));
    }
    
    public void collectBaseStats(StatMap stats, int playerLevel) {
        CompactBaseStats.collectStats(stats, getCompactBaseStats(playerLevel));
    }
    */
    
    public long getCompactBaseStats(int playerLevel) {
        if (playerLevel < 1 || playerLevel > baseStats.length) {
            throw new IllegalArgumentException("Invalid Player Level: " + playerLevel);
        }
        return baseStats[playerLevel - 1];
    }
    
    @Override
    void registerNames(Consumer<String> c) {
        c.accept(shortName);
    }
    
    static class MailAt40 extends ClassT {
        public MailAt40(int id, String name, String shortName, String iconName, long allowedRaces, long allowedWeapons, long allowedArmors, long[] baseStats) {
            super(id, name, shortName, iconName, allowedRaces, allowedWeapons, allowedArmors, baseStats);
        }
        @Override
        public boolean canWear(int playerLevel, ArmorT armor) {
            return super.canWear(playerLevel, armor) | (playerLevel >= 40 && armor == ArmorT.MAIL);
        }        
    }    

    static public final ClassT WAR = new ClassT(
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
            ),
            new long[]{0x800800a01100bL,0x900900c01300cL,0xa00a00c01400cL,0xa00a00d01500dL,0xa00a00d01500dL,0xa00a00d01500dL,0xa00b00e01700eL,0xb00c00f01800fL,0xc00c01001a010L,0xc00d01101b011L,0xe00e01201e012L,0xf010014021014L,0x10011015023016L,0x11012018026018L,0x13014019029019L,0x1401501b02c01bL,0x1601701c02f01dL,0x1701901f03301fL,0x1901a021036021L,0x1a01b023038023L,0x1c01d02403b024L,0x1e01f02703f027L,0x1e020028042029L,0x2102202b04602bL,0x2202302d04902dL,0x2402502e04c02fL,0x2402603004f030L,0x26028033053033L,0x2802a035056035L,0x2902b036058036L,0x2b02c03805b038L,0x2b02e03905e03aL,0x2d02f03c06103bL,0x3003203e06603fL,0x31033040069040L,0x3203504206c042L,0x3303604406f044L,0x35037045072046L,0x36039047074047L,0x3803b04907904aL,0x3903c04b07b04cL,0x3b03d04d07e04dL,0x3c03f04f08104fL,0x3e040050084051L,0x3f042053088053L,0x4104405608c056L,0x4304605708f058L,0x44047059092059L,0x4504805b09405bL,0x4604a05d09705dL,0x4804b05e09a05eL,0x4a04d06109e061L,0x4b04e0620a1063L,0x4c0500640a4064L,0x4d0510660a7066L,0x500530690ab069L,0x5105506a0ae06aL,0x5205606c0b006cL,0x5305706d0b306eL,0x550590700b7070L,0x5b0600780c4078L,0x6106607f0d0080L,0x6706c0870dd087L,0x6806d0890e0089L,0x6a06f08b0e408cL,0x6c07108d0e708dL,0x6d07208f0ea08fL,0x6e0730910ec091L,0x6f0750920ef092L,0x7507b09a0fc09aL,0x7907e09e10309eL,0x7f0840a610f0a6L,0x8408b0ae11c0aeL,0x8608c0af11f0afL,0x8708d0b11210b1L,0x8808f0b31240b3L,0x8a0910b51280b5L,0x8c0920b712b0b7L,0x8e0940ba12f0baL,0x900970bd1350bdL,0x9609d0c51420c5L,0x9b0a30cc14e0ccL,0x9f0a70d01550d1L,0xa10a80d31580d2L,0xa10a90d415b0d4L,0xa80af0dc1670dcL,0xae0b60e31740e3L,0xb30bc0eb1800ebL,0xb90c20f218d0f3L,0xbf0c80fa1990faL,0x105112157230157L,0x135144195296195L,0x16917b1da3071daL,0x1a81bc22b38c22bL,0x1f120928c42a28cL,0x2492652ff4e62ffL,0x26127e31d51831dL,0x27829633c54b33cL,0x28f2af35b57d35bL,0x2a72c73795af37aL}
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
            ),
            null
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
            ),
            null
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
            ),
            null
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
            ),
            null   
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
            ),
            null
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
                    ArmorT.SHIELD
            ),
            null
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
            ),
            null
    );
    static public final ClassT LOCK = new ClassT(
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
            ),
            null
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
            ),
            null
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
            ),
            new long[]{0x900c00f00700bL,0xa00d01100800cL,0xb00e01200900cL,0xb00f01300900dL,0xb00f01300900dL,0xb00f01300900dL,0xc01001400a00eL,0xd01101500a00fL,0xe01201700b010L,0xe01301800c011L,0x1001501a00d012L,0x1101701d00e014L,0x1301901f00f016L,0x1401b022010018L,0x1601d024012019L,0x1701f02701301bL,0x1902102901401dL,0x1b02402d01601fL,0x1d02602f017021L,0x1e028032018023L,0x2002a034019024L,0x2202d03801b027L,0x2302f03a01c029L,0x2603203e01e02bL,0x2703404101f02dL,0x2903604302102fL,0x2a038045022030L,0x2c03b049024033L,0x2e03d04c025035L,0x2f03f04e026036L,0x31041051027038L,0x3204305302803aL,0x3404505602a03bL,0x3704905a02c03fL,0x3804b05d02d040L,0x3a04d05f02e042L,0x3b04f062030044L,0x3d051064031046L,0x3e053067032047L,0x4105606a03404aL,0x4205806d03504cL,0x4405a06f03604dL,0x4505c07203804fL,0x4705e074039051L,0x4906107803b053L,0x4b06407c03c056L,0x4d06607e03e058L,0x4e06808103f059L,0x5006a08304005bL,0x5106c08604105dL,0x5306e08804205eL,0x5507108c044061L,0x5607308e045063L,0x58075091047064L,0x59077093048066L,0x5c07a09704a069L,0x5d07c09904b06aL,0x5f07e09c04c06cL,0x6008009e04d06eL,0x620830a204f070L,0x6908c0ad054078L,0x700950b805a080L,0x7709e0c305f087L,0x780a00c6060089L,0x7a0a30c906208cL,0x7c0a50cc06308dL,0x7d0a70ce06508fL,0x7f0a90d1066091L,0x800ab0d3067092L,0x870b40de06c09aL,0x8b0b90e406f09eL,0x920c20f00750a6L,0x980cb0fb07a0aeL,0x9a0cd0fd07c0afL,0x9b0cf10007c0b1L,0x9d0d110207e0b3L,0x9f0d410607f0b5L,0xa10d61080810b7L,0xa30d910c0820baL,0xa60dd1110850bdL,0xad0e611c08b0c5L,0xb30ef1270900ccL,0xb70f412d0930d1L,0xb90f61300940d2L,0xba0f81320950d4L,0xc110113d09a0dcL,0xc810a1480a00e3L,0xce1131530a50ebL,0xd511c15e0ab0f3L,0xdc1251690b00faL,0x12d1911ef0f1157L,0x1641da24911d195L,0x1a022b2ac14d1daL,0x1e828a32118722bL,0x23c2fb3ad1cb28cL,0x2a238245321c2ffL,0x2bd3a647f23131dL,0x2d83ca4ac24733cL,0x2f33ee4d825d35bL,0x30e41250427237aL}
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
