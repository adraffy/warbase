package warbase.types;

public class EquipT extends TypeT { 
    
    public final double armorMod;
    public final int randPropIndex;
    public final boolean twoHand;
    
    static private int _index;
    public EquipT(int id, String name, double armorMod, double armorMod2, int randPropIndex, boolean twoHand) {
        super(_index++, id, name);
        this.armorMod = armorMod;
        this.randPropIndex = randPropIndex;
        this.twoHand = twoHand;
    }
    
    static public final EquipT NONE         = new EquipT(0, "None",             0.00, 0.00, -1, false);
    static public final EquipT HEAD         = new EquipT(1, "Head",             0.13, 0.12,  0, false);
    static public final EquipT NECK         = new EquipT(2, "Neck",             0.00, 0.00,  2, false);
    static public final EquipT SHOULDER     = new EquipT(3, "Shoulder" ,        0.12, 0.13,  1, false);
    static public final EquipT SHIRT        = new EquipT(4, "Shirt",            0.00, 0.00, -1, false);
    static public final EquipT CHEST        = new EquipT(5, "Chest",            0.16, 0.16,  0, false);
    static public final EquipT WAIST        = new EquipT(6, "Waist",            0.09, 0.09,  1, false);
    static public final EquipT LEGS         = new EquipT(7, "Legs",             0.14, 0.14,  0, false);
    static public final EquipT FEET         = new EquipT(8, "Feet",             0.11, 0.11,  1, false);
    static public final EquipT WRIST        = new EquipT(9, "Wrist",            0.07, 0.10,  2, false);
    static public final EquipT HANDS        = new EquipT(10,"Hands",            0.10, 0.10,  1, false);
    static public final EquipT FINGER       = new EquipT(11,"Finger",           0.00, 0.00,  2, false);
    static public final EquipT TRINKET      = new EquipT(12,"Trinket",          0.00, 0.00,  1, false);
    static public final EquipT ONE_HAND     = new EquipT(13,"One-Hand",         0.00, 0.00, -1, false);
    static public final EquipT SHIELD       = new EquipT(14,"Shield",           0.00, 0.00,  2, false);
    static public final EquipT RANGED       = new EquipT(15,"Ranged",           0.00, 0.00, -1, true);
    static public final EquipT CLOAK        = new EquipT(16,"Cloak",            0.08, 0.08,  2, false);
    static public final EquipT TWO_HAND     = new EquipT(17,"Two-Hand",         0.00, 0.00, -1, true);
    static public final EquipT TABARD       = new EquipT(19,"Tabard",           0.00, 0.00, -1, false);
    static public final EquipT ROBE         = new EquipT(20,"Robe",             0.16, 0.16,  0, false);
    static public final EquipT MAIN_HAND    = new EquipT(21,"Main Hand",        0.00, 0.00, -1, false);
    static public final EquipT OFF_HAND     = new EquipT(22,"Off Hand",         0.00, 0.00,  2, false);
    static public final EquipT HELD_IN_OFF  = new EquipT(23,"Held in Off-hand", 0.00, 0.00,  2, false);
    //static public final EquipT THROWN       = new EquipT(25,"Thrown",           0.00, 0.00, -1, false);
    static public final EquipT RANGED_2     = new EquipT(26,"Ranged",           0.00, 0.00, -1, false); // wand but some bows use it
    //static public final EquipT RELIC        = new EquipT(28,"Relic",            0.00, 0.00, -1, false);
    
    static public class Dead {
        static public final int BAG     = 18;
        static public final int AMMO    = 24;
        static public final int THROWN  = 25;
        static public final int QUIVER  = 27;
        static public final int RELIC   = 28;
        static public boolean test(int id) {
            switch (id) {
                case BAG:
                case AMMO:
                case THROWN:
                case QUIVER:
                case RELIC:
                    return true;
                default:
                    return false;
            }
        }
    }
    
    static public final BitContainer<EquipT> db = new BitContainer<>(EquipT.class);
    
}
