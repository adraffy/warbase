package com.antistupid.warbase.types;

import com.antistupid.warbase.stats.CompactBaseStats;
import com.antistupid.warbase.stats.StatMap;
import java.awt.Color;
import java.util.ArrayList;
import java.util.function.Consumer;


public class ClassT extends TypeT {

    public final String shortName;
    public final String iconName;
    public final Color fillColor;
    public final Color textColor;
    
    final long allowedRaces;
    final long allowedWeapons;
    final long allowedArmors;
    final long[] baseStats;
    
    public final ArrayList<SpecT> specs = new ArrayList<>(4);
    public final ArrayList<RaceT> races = new ArrayList<>(RaceT.db.size());
    
    static private int _index;
    private ClassT(int id, String name, String shortName, String iconName, Color fillColor, Color textColor, long allowedRaces, long allowedWeapons, long allowedArmors, long[] baseStats) {
        super(_index++, id, name);
        this.shortName = shortName != null ? shortName : name;
        this.iconName = iconName;
        this.fillColor = fillColor;
        this.textColor = textColor != null ? textColor : fillColor;
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
        public MailAt40(int id, String name, String shortName, String iconName, Color fillColor, Color textColor, long allowedRaces, long allowedWeapons, long allowedArmors, long[] baseStats) {
            super(id, name, shortName, iconName, fillColor, textColor, allowedRaces, allowedWeapons, allowedArmors, baseStats);
        }
        @Override
        public boolean canWear(int playerLevel, ArmorT armor) {
            return super.canWear(playerLevel, armor) | (playerLevel >= 40 && armor == ArmorT.MAIL);
        }        
    }    
    
    static public final ClassT WAR = new ClassT(
            1, "Warrior", "War", "class_warrior", 
            new Color(0xC6, 0x9B, 0x6D),
            null,
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
            2, "Paladin", "Pally", "class_paladin",
            new Color(0xF4, 0x8C, 0xBA),
            null,
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
            new long[]{0x900c00501100bL,0xa00d00601300cL,0xb00e00601400cL,0xb00f00701500dL,0xb00f00701500dL,0xb00f00701500dL,0xc01000701700eL,0xd01100701800fL,0xe01200801a010L,0xe01300901b011L,0x1001500901e012L,0x1101700a021014L,0x1301900b023016L,0x1401b00c026018L,0x1601d00d029019L,0x1701f00e02c01bL,0x1902100f02f01dL,0x1b02401003301fL,0x1d026011036021L,0x1e028012038023L,0x2002a01203b024L,0x2202d01403f027L,0x2302f015042029L,0x2603201604602bL,0x2703401704902dL,0x2903601804c02fL,0x2a03801804f030L,0x2c03b01a053033L,0x2e03d01b056035L,0x2f03f01c058036L,0x3104101d05b038L,0x3204301d05e03aL,0x3404501e06103bL,0x3704902006603fL,0x3804b021069040L,0x3a04d02206c042L,0x3b04f02306f044L,0x3d051023072046L,0x3e053025074047L,0x4105602607904aL,0x4205802707b04cL,0x4405a02707e04dL,0x4505c02808104fL,0x4705e029084051L,0x4906102b088053L,0x4b06402c08c056L,0x4d06602d08f058L,0x4e06802e092059L,0x5006a02e09405bL,0x5106c03009705dL,0x5306e03009a05eL,0x5507103209e061L,0x560730320a1063L,0x580750330a4064L,0x590770340a7066L,0x5c07a0360ab069L,0x5d07c0360ae06aL,0x5f07e0370b006cL,0x600800380b306eL,0x620830390b7070L,0x6908c03d0c4078L,0x700950410d0080L,0x7709e0450dd087L,0x780a00460e0089L,0x7a0a30470e408cL,0x7c0a50480e708dL,0x7d0a70490ea08fL,0x7f0a904a0ec091L,0x800ab04b0ef092L,0x870b404f0fc09aL,0x8b0b905110309eL,0x920c205510f0a6L,0x980cb05911c0aeL,0x9a0cd05a11f0afL,0x9b0cf05b1210b1L,0x9d0d105b1240b3L,0x9f0d405d1280b5L,0xa10d605e12b0b7L,0xa30d905f12f0baL,0xa60dd0611350bdL,0xad0e60651420c5L,0xb30ef06914e0ccL,0xb70f406b1550d1L,0xb90f606c1580d2L,0xba0f806c15b0d4L,0xc11010701670dcL,0xc810a0741740e3L,0xce1130781800ebL,0xd511c07c18d0f3L,0xdc1250801990faL,0x12d1910b0230157L,0x1641da0cf296195L,0x1a022b0f33071daL,0x1e828a11c38c22bL,0x23c2fb14e42a28cL,0x2a23821894e62ffL,0x2bd3a619851831dL,0x2d83ca1a854b33cL,0x2f33ee1b857d35bL,0x30e4121c75af37aL}
    );
    static public final MailAt40 HUNTER = new MailAt40(
            3, "Hunter", "Hunt", "class_hunter",
            new Color(0xAA, 0xD3, 0x72),
            null,
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
            new long[]{0x800a00f00a00bL,0x900b01100c00cL,0xa00b01200c00cL,0xa00c01300d00dL,0xa00c01300d00dL,0xa00c01300d00dL,0xb00d01400e00eL,0xc00e01500f00fL,0xd00f017010010L,0xd010018010011L,0xf01101a012012L,0xf01301d014014L,0x1101401f015016L,0x12016022017018L,0x14018024019019L,0x1501902701b01bL,0x1701b02901d01dL,0x1901d02d01f01fL,0x1a01f02f021021L,0x1b021032022023L,0x1d022034024024L,0x1f025038026027L,0x2002603a028029L,0x2302903e02b02bL,0x2302b04102c02dL,0x2502c04302e02fL,0x2602e045030030L,0x28030049033033L,0x2a03204c034035L,0x2b03404e036036L,0x2d035051037038L,0x2d03705303903aL,0x2f03905603b03bL,0x3203c05a03e03fL,0x3303d05d040040L,0x3503f05f042042L,0x36041062044044L,0x37042064045046L,0x38044067047047L,0x3b04606a04a04aL,0x3c04806d04b04cL,0x3e04a06f04d04dL,0x3f04b07204f04fL,0x4104d074050051L,0x4204f078053053L,0x4405207c055056L,0x4605407e057058L,0x47055081059059L,0x4905708305a05bL,0x4a05808605c05dL,0x4b05a08805e05eL,0x4d05d08c060061L,0x4e05e08e062063L,0x50060091064064L,0x51061093066066L,0x54064097068069L,0x5506609906a06aL,0x5606709c06b06cL,0x5706909e06d06eL,0x5906b0a206f070L,0x5f0730ad077078L,0x6607a0b807f080L,0x6c0810c3087087L,0x6d0830c6088089L,0x6f0860c908b08cL,0x710870cc08d08dL,0x720890ce08e08fL,0x7308a0d1090091L,0x7408c0d3092092L,0x7b0930de09909aL,0x7e0980e409e09eL,0x8509f0f00a50a6L,0x8a0a60fb0ad0aeL,0x8c0a80fd0af0afL,0x8d0aa1000b00b1L,0x8f0ab1020b20b3L,0x910ae1060b40b5L,0x920af1080b60b7L,0x940b210c0b80baL,0x970b51110bc0bdL,0x9d0bc11c0c40c5L,0xa30c41270cb0ccL,0xa60c812d0d00d1L,0xa80ca1300d10d2L,0xa90cb1320d30d4L,0xaf0d313d0db0dcL,0xb60da1480e20e3L,0xbb0e11530ea0ebL,0xc20e915e0f20f3L,0xc80f01690f90faL,0x1121481ef155157L,0x144184249193195L,0x17a1c72ac1d81daL,0x1bc21432122922bL,0x2082713ad28928cL,0x2652e04532fb2ffL,0x27d2fd47f31a31dL,0x29631b4ac33933cL,0x2ae3384d835735bL,0x2c735650437637aL}
    );
    static public final ClassT ROGUE = new ClassT(
            4, "Rogue", null, "class_rogue",    
            new Color(0xFF, 0xF4, 0x68),
            null,
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
            new long[]{0x600800f00e00bL,0x700901101000cL,0x800a01201100cL,0x800a01301100dL,0x800a01301100dL,0x800a01301100dL,0x800b01401300eL,0x900c01501400fL,0xa00c017016010L,0xa00d018016011L,0xb00e01a019012L,0xc01001d01b014L,0xd01101f01d016L,0xe01202201f018L,0xf014024022019L,0x1001502702401bL,0x1101702902701dL,0x1201902d02a01fL,0x1401a02f02d021L,0x1401b03202e023L,0x1601d034031024L,0x1701f038034027L,0x1802003a037029L,0x1a02203e03a02bL,0x1b02304103d02dL,0x1c02504303f02fL,0x1d026045041030L,0x1e028049045033L,0x1f02a04c047035L,0x2002b04e049036L,0x2102c05104b038L,0x2202e05304e03aL,0x2302f05605003bL,0x2603205a05503fL,0x2603305d057040L,0x2803505f05a042L,0x2803606205c044L,0x2a03706405e046L,0x2a039067060047L,0x2c03b06a06404aL,0x2d03c06d06604cL,0x2e03d06f06804dL,0x2f03f07206b04fL,0x3004007406d051L,0x32042078071053L,0x3304407c074056L,0x3504607e077058L,0x35047081079059L,0x3704808307b05bL,0x3704a08607d05dL,0x3904b08808005eL,0x3a04d08c083061L,0x3b04e08e085063L,0x3c050091088064L,0x3d05109308a066L,0x3f05309708e069L,0x3f05509909006aL,0x4105609c09206cL,0x4105709e09406eL,0x430590a2098070L,0x480600ad0a2078L,0x4c0660b80ac080L,0x5106c0c30b7087L,0x5206d0c60ba089L,0x5306f0c90bd08cL,0x550710cc0bf08dL,0x550720ce0c208fL,0x570730d10c4091L,0x570750d30c6092L,0x5c07b0de0d109aL,0x5f07e0e40d709eL,0x640840f00e10a6L,0x6808b0fb0eb0aeL,0x6908c0fd0ee0afL,0x6a08d1000f00b1L,0x6b08f1020f20b3L,0x6c0911060f50b5L,0x6e0921080f80b7L,0x6f09410c0fb0baL,0x710971111000bdL,0x7609d11c10b0c5L,0x7a0a31271150ccL,0x7d0a712d11b0d1L,0x7e0a813011d0d2L,0x7f0a91321200d4L,0x840af13d12a0dcL,0x880b61481340e3L,0x8c0bc15313e0ebL,0x910c215e1490f3L,0x960c81691530faL,0xcd1121ef1d0157L,0xf3144249225195L,0x11c17b2ac2821daL,0x14d1bc3212f122bL,0x1862093ad37428cL,0x1cc26545340f2ffL,0x1de27e47f43931dL,0x1f02964ac46333cL,0x2032af4d848d35bL,0x2152c75044b637aL}
    );
    static public final ClassT PRIEST = new ClassT(
            5, "Priest", null, "class_priest", 
            Color.WHITE,
            Color.BLACK,
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
            new long[]{0x900c00c00a00bL,0xa00d00e00b00cL,0xb00e00f00c00cL,0xb00f01000c00dL,0xb00f01000c00dL,0xb00f01000c00dL,0xc01001100d00eL,0xd01101100e00fL,0xe01201300f010L,0xe013014010011L,0x10015016011012L,0x11017018013014L,0x1301901a014016L,0x1401b01c016018L,0x1601d01e018019L,0x1701f02001901bL,0x1902102201b01dL,0x1b02402501e01fL,0x1d02602701f021L,0x1e02802a020023L,0x2002a02b022024L,0x2202d02f025027L,0x2302f030026029L,0x2603203402902bL,0x2703403602a02dL,0x2903603802c02fL,0x2a03803902e030L,0x2c03b03d030033L,0x2e03d03f032035L,0x2f03f041033036L,0x31041043035038L,0x3204304503603aL,0x3404504703803bL,0x3704904b03b03fL,0x3804b04d03d040L,0x3a04d04f03f042L,0x3b04f051040044L,0x3d051053042046L,0x3e053056043047L,0x4105605804604aL,0x4205805b04704cL,0x4405a05c04904dL,0x4505c05f04b04fL,0x4705e06004c051L,0x4906106404f053L,0x4b064067051056L,0x4d066069053058L,0x4e06806b055059L,0x5006a06d05605bL,0x5106c06f05705dL,0x5306e07105905eL,0x5507107405c061L,0x5607307605d063L,0x5807507805f064L,0x5907707a061066L,0x5c07a07d063069L,0x5d07c07f06506aL,0x5f07e08206606cL,0x6008008306806eL,0x6208308706a070L,0x6908c090072078L,0x70095099079080L,0x7709e0a2080087L,0x780a00a5082089L,0x7a0a30a708408cL,0x7c0a50aa08608dL,0x7d0a70ab08808fL,0x7f0a90ae089091L,0x800ab0af08a092L,0x870b40b809209aL,0x8b0b90bd09609eL,0x920c20c709d0a6L,0x980cb0d10a50aeL,0x9a0cd0d20a60afL,0x9b0cf0d50a70b1L,0x9d0d10d60a90b3L,0x9f0d40da0ac0b5L,0xa10d60db0ad0b7L,0xa30d90df0b00baL,0xa60dd0e30b30bdL,0xad0e60ec0bb0c5L,0xb30ef0f50c20ccL,0xb70f40fa0c60d1L,0xb90f60fd0c70d2L,0xba0f80fe0c90d4L,0xc11011070d00dcL,0xc810a1110d80e3L,0xce11311a0df0ebL,0xd511c1230e60f3L,0xdc12512c0ed0faL,0x12d19119b144157L,0x1641da1e6180195L,0x1a022b2381c11daL,0x1e828a29a20e22bL,0x23c2fb30e26a28cL,0x2a23823982d72ffL,0x2bd3a63bd2f431dL,0x2d83ca3e231133cL,0x2f33ee40632e35bL,0x30e41242b34b37aL}   
    );
    static public final ClassT DK  = new ClassT(
            6, "Death Knight", "DK", "class_deathknight",
            new Color(0xC4, 0x1E, 0x3B),
            null,
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
            new long[]{0x700700d01100bL,0x800700e01300cL,0x900800f01400cL,0x900801001500dL,0x900801001500dL,0x900801001500dL,0xa00901101700eL,0xb00901201800fL,0xb00a01301a010L,0xb00a01401b011L,0xd00b01601e012L,0xe00d018021014L,0x1000e01a023016L,0x1000f01c026018L,0x1201001e029019L,0x1301102102c01bL,0x1401202202f01dL,0x1601402603301fL,0x18015027036021L,0x1901602a038023L,0x1a01702b03b024L,0x1c01902f03f027L,0x1d01a030042029L,0x1f01b03404602bL,0x2001c03604902dL,0x2201d03804c02fL,0x2201f03a04f030L,0x2402003d053033L,0x2602103f056035L,0x26022041058036L,0x2802304405b038L,0x2902504505e03aL,0x2b02604806103bL,0x2d02804b06603fL,0x2e02904e069040L,0x2f02a04f06c042L,0x3002b05206f044L,0x3202c053072046L,0x3302d056074047L,0x3502f05807904aL,0x3603005b07b04cL,0x3803105d07e04dL,0x3803205f08104fL,0x3a033061084051L,0x3c035064088053L,0x3d03706708c056L,0x3f03806908f058L,0x4003906c092059L,0x4103a06d09405bL,0x4203b07009705dL,0x4403c07109a05eL,0x4603e07509e061L,0x4603f0760a1063L,0x480400790a4064L,0x4904107b0a7066L,0x4b04307e0ab069L,0x4c0440800ae06aL,0x4e0450820b006cL,0x4f0460840b306eL,0x500480870b7070L,0x5604c0900c4078L,0x5c0510990d0080L,0x610560a30dd087L,0x620570a50e0089L,0x640590a80e408cL,0x6505a0aa0e708dL,0x6605b0ac0ea08fL,0x6805c0ae0ec091L,0x6905d0b00ef092L,0x6e0620b90fc09aL,0x720650be10309eL,0x7706a0c810f0a6L,0x7c06f0d111c0aeL,0x7e0700d311f0afL,0x7f0710d51210b1L,0x800720d71240b3L,0x820740da1280b5L,0x840750dc12b0b7L,0x850760df12f0baL,0x880790e41350bdL,0x8e07e0ed1420c5L,0x920830f614e0ccL,0x960850fb1550d1L,0x970860fd1580d2L,0x980870ff15b0d4L,0x9e08c1081670dcL,0xa40911111740e3L,0xa909611b1800ebL,0xae09b12418d0f3L,0xb40a012d1990faL,0xf60db19d230157L,0x1231031e8296195L,0x15412f23a3071daL,0x18f16329c38c22bL,0x1d41a131142a28cL,0x2271ea39b4e62ffL,0x23e1fe3c051831dL,0x2542123e554b33cL,0x26a22540a57d35bL,0x28023942f5af37aL}
    );
    static public final MailAt40 SHAMAN = new MailAt40(
            7, "Shaman", "Sham", "class_shaman",
            new Color(0x47, 0x74, 0xFF), //0x23, 0x59, 0xFF),//{"0x6c", "0x90", "0xff"}
            null,
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
            new long[]{0x900c00f00700bL,0xa00d01100800cL,0xb00e01200900cL,0xb00f01300900dL,0xb00f01300900dL,0xb00f01300900dL,0xc01001400a00eL,0xd01101500a00fL,0xe01201700b010L,0xe01301800c011L,0x1001501a00d012L,0x1101701d00e014L,0x1301901f00f016L,0x1401b022010018L,0x1601d024012019L,0x1701f02701301bL,0x1902102901401dL,0x1b02402d01601fL,0x1d02602f017021L,0x1e028032018023L,0x2002a034019024L,0x2202d03801b027L,0x2302f03a01c029L,0x2603203e01e02bL,0x2703404101f02dL,0x2903604302102fL,0x2a038045022030L,0x2c03b049024033L,0x2e03d04c025035L,0x2f03f04e026036L,0x31041051027038L,0x3204305302803aL,0x3404505602a03bL,0x3704905a02c03fL,0x3804b05d02d040L,0x3a04d05f02e042L,0x3b04f062030044L,0x3d051064031046L,0x3e053067032047L,0x4105606a03404aL,0x4205806d03504cL,0x4405a06f03604dL,0x4505c07203804fL,0x4705e074039051L,0x4906107803b053L,0x4b06407c03c056L,0x4d06607e03e058L,0x4e06808103f059L,0x5006a08304005bL,0x5106c08604105dL,0x5306e08804205eL,0x5507108c044061L,0x5607308e045063L,0x58075091047064L,0x59077093048066L,0x5c07a09704a069L,0x5d07c09904b06aL,0x5f07e09c04c06cL,0x6008009e04d06eL,0x620830a204f070L,0x6908c0ad054078L,0x700950b805a080L,0x7709e0c305f087L,0x780a00c6060089L,0x7a0a30c906208cL,0x7c0a50cc06308dL,0x7d0a70ce06508fL,0x7f0a90d1066091L,0x800ab0d3067092L,0x870b40de06c09aL,0x8b0b90e406f09eL,0x920c20f00750a6L,0x980cb0fb07a0aeL,0x9a0cd0fd07c0afL,0x9b0cf10007c0b1L,0x9d0d110207e0b3L,0x9f0d410607f0b5L,0xa10d61080810b7L,0xa30d910c0820baL,0xa60dd1110850bdL,0xad0e611c08b0c5L,0xb30ef1270900ccL,0xb70f412d0930d1L,0xb90f61300940d2L,0xba0f81320950d4L,0xc110113d09a0dcL,0xc810a1480a00e3L,0xce1131530a50ebL,0xd511c15e0ab0f3L,0xdc1251690b00faL,0x12d1911ef0f1157L,0x1641da24911d195L,0x1a022b2ac14d1daL,0x1e828a32118722bL,0x23c2fb3ad1cb28cL,0x2a238245321c2ffL,0x2bd3a647f23131dL,0x2d83ca4ac24733cL,0x2f33ee4d825d35bL,0x30e41250427237aL}
    );           
    static public final ClassT MAGE = new ClassT(
            8, "Mage", null, "class_mage",
            new Color(0x68, 0xCC, 0xEF),
            null,
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
            new long[]{0xd00c00a00800bL,0xf00d00c00800cL,0x1000e00c00900cL,0x1000f00d00900dL,0x1000f00d00900dL,0x1000f00d00900dL,0x1201000e00a00eL,0x1301100f00b00fL,0x1501201000c010L,0x1501301100c011L,0x1801501200d012L,0x1901701400f014L,0x1c019015010016L,0x1e01b018011018L,0x2101d019012019L,0x2201f01b01401bL,0x2502101c01501dL,0x2802401f01701fL,0x2b026021018021L,0x2c028023019023L,0x2f02a02401a024L,0x3202d02701c027L,0x3402f02801d029L,0x3803202b01f02bL,0x3a03402d02002dL,0x3d03602e02202fL,0x3e038030023030L,0x4103b033025033L,0x4403d035026035L,0x4503f036027036L,0x48041038028038L,0x4a04303902a03aL,0x4d04503c02b03bL,0x5104903e02d03fL,0x5304b04002f040L,0x5604d042030042L,0x5704f044031044L,0x5a051045033046L,0x5c053047034047L,0x6005604903604aL,0x6205804b03704cL,0x6405a04d03804dL,0x6605c04f03904fL,0x6905e05003b051L,0x6c06105303d053L,0x6f06405603e056L,0x72066057040058L,0x73068059041059L,0x7606a05b04205bL,0x7806c05d04305dL,0x7b06e05e04505eL,0x7e071061046061L,0x7f073062048063L,0x82075064049064L,0x8307706604a066L,0x8807a06904c069L,0x8907c06a04d06aL,0x8c07e06c04e06cL,0x8e08006d05006eL,0x91083070051070L,0x9b08c078057078L,0xa509507f05d080L,0xb009e087062087L,0xb10a0089064089L,0xb40a308b06508cL,0xb70a508d06708dL,0xb90a708f06808fL,0xbc0a9091069091L,0xbd0ab09206a092L,0xc70b409a07009aL,0xcd0b909e07309eL,0xd80c20a60790a6L,0xe10cb0ae07e0aeL,0xe40cd0af0800afL,0xe50cf0b10810b1L,0xe80d10b30820b3L,0xeb0d40b50840b5L,0xee0d60b70850b7L,0xf10d90ba0870baL,0xf50dd0bd08a0bdL,0x1000e60c508f0c5L,0x1080ef0cc0950ccL,0x10e0f40d00980d1L,0x1110f60d30990d2L,0x1130f80d409a0d4L,0x11d1010dc0a00dcL,0x12710a0e30a60e3L,0x1301130eb0ab0ebL,0x13b11c0f20b10f3L,0x1451250fa0b60faL,0x1bd1911570f9157L,0x20e1da195127195L,0x26722b1da1591daL,0x2d128a22b19422bL,0x34d2fb28c1da28cL,0x3e43822ff22e2ffL,0x40c3a631d24431dL,0x4333ca33c25b33cL,0x45b3ee35b27135bL,0x48341237928737aL}
    );
    static public final ClassT LOCK = new ClassT(
            9, "Warlock", "Lock", "class_warlock",
            new Color(0x93, 0x82, 0xC9),
            null,
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
            new long[]{0xd00c00c00600bL,0xf00d00d00700cL,0x1000e00e00800cL,0x1000f00f00800dL,0x1000f00f00800dL,0x1000f00f00800dL,0x1201000f00900eL,0x1301101000900fL,0x1501201200a010L,0x1501301200a011L,0x1801501400b012L,0x1901701600d014L,0x1c01901800d016L,0x1e01b01a00e018L,0x2101d01c010019L,0x2201f01e01101bL,0x2502101f01201dL,0x2802402301301fL,0x2b026024014021L,0x2c028026015023L,0x2f02a028016024L,0x3202d02b018027L,0x3402f02d019029L,0x3803203001b02bL,0x3a03403201c02dL,0x3d03603301d02fL,0x3e03803501e030L,0x4103b03801f033L,0x4403d03a021035L,0x4503f03c021036L,0x4804103e022038L,0x4a04304002403aL,0x4d04504202503bL,0x5104904502703fL,0x5304b047028040L,0x5604d049029042L,0x5704f04b02a044L,0x5a05104d02b046L,0x5c05304f02c047L,0x6005605102e04aL,0x6205805402f04cL,0x6405a05503004dL,0x6605c05703104fL,0x6905e059032051L,0x6c06105c034053L,0x6f06405f035056L,0x72066061036058L,0x73068063037059L,0x7606a06503805bL,0x7806c06703905dL,0x7b06e06803a05eL,0x7e07106b03c061L,0x7f07306d03d063L,0x8207506f03e064L,0x8307707103f066L,0x8807a074041069L,0x8907c07504206aL,0x8c07e07804306cL,0x8e08007904406eL,0x9108307c045070L,0x9b08c08504a078L,0xa509508d04f080L,0xb009e096054087L,0xb10a0098055089L,0xb40a309a05608cL,0xb70a509d05808dL,0xb90a709e05908fL,0xbc0a90a0059091L,0xbd0ab0a205b092L,0xc70b40aa06009aL,0xcd0b90af06209eL,0xd80c20b80670a6L,0xe10cb0c106c0aeL,0xe40cd0c206d0afL,0xe50cf0c406e0b1L,0xe80d10c606f0b3L,0xeb0d40c90700b5L,0xee0d60cb0710b7L,0xf10d90ce0730baL,0xf50dd0d10750bdL,0x1000e60da07a0c5L,0x1080ef0e207f0ccL,0x10e0f40e70810d1L,0x1110f60e90820d2L,0x1130f80eb0840d4L,0x11d1010f30880dcL,0x12710a0fc08d0e3L,0x1301131040920ebL,0x13b11c10d0960f3L,0x14512511509b0faL,0x1bd19117c0d4157L,0x20e1da1c10fb195L,0x26722b20d1261daL,0x2d128a26715822bL,0x34d2fb2d219428cL,0x3e43823511db2ffL,0x40c3a63731ee31dL,0x4333ca39620233cL,0x45b3ee3b721435bL,0x4834123d922737aL}
    );
    static public final ClassT MONK = new ClassT(
            10, "Monk", null, "class_monk",
            new Color(0x00, 0x84, 0x67),
            null,
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
            new long[]{0x900c00f00700bL,0xa00d01100800cL,0xb00e01200900cL,0xb00f01300900dL,0xb00f01300900dL,0xb00f01300900dL,0xc01001400a00eL,0xd01101500a00fL,0xe01201700b010L,0xe01301800c011L,0x1001501a00d012L,0x1101701d00e014L,0x1301901f00f016L,0x1401b022010018L,0x1601d024012019L,0x1701f02701301bL,0x1902102901401dL,0x1b02402d01601fL,0x1d02602f017021L,0x1e028032018023L,0x2002a034019024L,0x2202d03801b027L,0x2302f03a01c029L,0x2603203e01e02bL,0x2703404101f02dL,0x2903604302102fL,0x2a038045022030L,0x2c03b049024033L,0x2e03d04c025035L,0x2f03f04e026036L,0x31041051027038L,0x3204305302803aL,0x3404505602a03bL,0x3704905a02c03fL,0x3804b05d02d040L,0x3a04d05f02e042L,0x3b04f062030044L,0x3d051064031046L,0x3e053067032047L,0x4105606a03404aL,0x4205806d03504cL,0x4405a06f03604dL,0x4505c07203804fL,0x4705e074039051L,0x4906107803b053L,0x4b06407c03c056L,0x4d06607e03e058L,0x4e06808103f059L,0x5006a08304005bL,0x5106c08604105dL,0x5306e08804205eL,0x5507108c044061L,0x5607308e045063L,0x58075091047064L,0x59077093048066L,0x5c07a09704a069L,0x5d07c09904b06aL,0x5f07e09c04c06cL,0x6008009e04d06eL,0x620830a204f070L,0x6908c0ad054078L,0x700950b805a080L,0x7709e0c305f087L,0x780a00c6060089L,0x7a0a30c906208cL,0x7c0a50cc06308dL,0x7d0a70ce06508fL,0x7f0a90d1066091L,0x800ab0d3067092L,0x870b40de06c09aL,0x8b0b90e406f09eL,0x920c20f00750a6L,0x980cb0fb07a0aeL,0x9a0cd0fd07c0afL,0x9b0cf10007c0b1L,0x9d0d110207e0b3L,0x9f0d410607f0b5L,0xa10d61080810b7L,0xa30d910c0820baL,0xa60dd1110850bdL,0xad0e611c08b0c5L,0xb30ef1270900ccL,0xb70f412d0930d1L,0xb90f61300940d2L,0xba0f81320950d4L,0xc110113d09a0dcL,0xc810a1480a00e3L,0xce1131530a50ebL,0xd511c15e0ab0f3L,0xdc1251690b00faL,0x12d1911ef0f1157L,0x1641da24911d195L,0x1a022b2ac14d1daL,0x1e828a32118722bL,0x23c2fb3ad1cb28cL,0x2a238245321c2ffL,0x2bd3a647f23131dL,0x2d83ca4ac24733cL,0x2f33ee4d825d35bL,0x30e41250427237aL}
    );    
    static public final ClassT DRUID = new ClassT(
            11, "Druid", null, "class_druid", 
            new Color(0xFF, 0x7C, 0x0A),
            null,
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
