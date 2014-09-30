package warbase.types;

public class SlotT extends TypeT {

    public final int bodyPart;
    public final long allowedEquip;
  
    static private int _index;
    private SlotT(int id, String name, int bodyPart, long equip) {
        super(_index++, id, name);
        this.bodyPart = bodyPart;
        this.allowedEquip = equip;
    }
    
    public boolean canContain(EquipT type) {
        return type != null && (allowedEquip & type.getBit()) != 0;
    }
    
    //
    
    static public final int BODYPART_ARMOR      = 0x01;
    static public final int BODYPART_HAND       = 0x02;
    static public final int BODYPART_JEWELRY    = 0x04;
    static public final int BODYPART_CLOTH      = 0x08;
        
    // filters
    static final int BODYPARTS_EXCEPT_HAND  = BODYPART_ARMOR | BODYPART_CLOTH | BODYPART_JEWELRY;
    static final int BODYPARTS_ALL      = -1;
    
    static public final SlotT HEAD      = new SlotT(1,  "Head",         BODYPART_ARMOR,     EquipT.db.encode(EquipT.HEAD));  
    static public final SlotT NECK      = new SlotT(2,  "Neck",         BODYPART_JEWELRY,   EquipT.db.encode(EquipT.NECK));    
    static public final SlotT SHOULDER  = new SlotT(3,  "Shoulder",     BODYPART_ARMOR,     EquipT.db.encode(EquipT.SHOULDER));
    static public final SlotT SHIRT     = new SlotT(4,  "Shirt",        BODYPART_CLOTH,     EquipT.db.encode(EquipT.SHIRT));
    static public final SlotT CHEST     = new SlotT(5,  "Chest",        BODYPART_ARMOR,     EquipT.db.encode(EquipT.CHEST, EquipT.ROBE));
    static public final SlotT WAIST     = new SlotT(6,  "Waist",        BODYPART_ARMOR,     EquipT.db.encode(EquipT.WAIST));
    static public final SlotT LEGS      = new SlotT(7,  "Legs",         BODYPART_ARMOR,     EquipT.db.encode(EquipT.LEGS));
    static public final SlotT FEET      = new SlotT(8,  "Feet",         BODYPART_ARMOR,     EquipT.db.encode(EquipT.FEET));
    static public final SlotT WRIST     = new SlotT(9,  "Wrist",        BODYPART_ARMOR,     EquipT.db.encode(EquipT.WRIST));
    static public final SlotT HANDS     = new SlotT(10, "Hands",        BODYPART_ARMOR,     EquipT.db.encode(EquipT.HANDS));
    static public final SlotT FINGER_1  = new SlotT(11, "Finger 1",     BODYPART_JEWELRY,   EquipT.db.encode(EquipT.FINGER));
    static public final SlotT FINGER_2  = new SlotT(12, "Finger 2",     BODYPART_JEWELRY,   EquipT.db.encode(EquipT.FINGER));
    static public final SlotT TRINKET_1 = new SlotT(13, "Trinket 1",    BODYPART_JEWELRY,   EquipT.db.encode(EquipT.TRINKET));
    static public final SlotT TRINKET_2 = new SlotT(14, "Trinket 2",    BODYPART_JEWELRY,   EquipT.db.encode(EquipT.TRINKET));
    static public final SlotT BACK      = new SlotT(15, "Back",         BODYPART_CLOTH,     EquipT.db.encode(EquipT.CLOAK));
    static public final SlotT MAIN_HAND = new SlotT(16, "Main Hand",    BODYPART_HAND,      EquipT.db.encode(EquipT.MAIN_HAND, EquipT.ONE_HAND, EquipT.TWO_HAND, EquipT.RANGED, EquipT.RANGED_2));
    static public final SlotT OFF_HAND  = new SlotT(17, "Off Hand",     BODYPART_HAND,      EquipT.db.encode(EquipT.ONE_HAND, EquipT.OFF_HAND, EquipT.SHIELD, EquipT.HELD_IN_OFF, EquipT.TWO_HAND));
    static public final SlotT TABARD    = new SlotT(19, "Tabard",       BODYPART_CLOTH,     EquipT.db.encode(EquipT.TABARD));
        
    static public final BitContainer<SlotT> db = new BitContainer<>(SlotT.class);
    
    /*
    static public SlotT firstMatch(EquipT equip) {
        for (SlotT x: db.types) {
            if (equip.memberOf(x.allowedEquip)) {
                return x;
            }            
        }
        return null;
    }
    */
    
}
