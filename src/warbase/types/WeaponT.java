package warbase.types;

public class WeaponT extends TypeT {

    public final String shortName;
    public final int randPropIndex;
    public final int hands; // number of hands required, 0 = refer to EquipT
    
    static private int __index;
    private WeaponT(int id, String name, String shortName, int hands, int randPropIndex) {
        super(__index++, id, name);
        this.shortName = shortName != null ? shortName : name;
        this.hands = hands;
        this.randPropIndex = randPropIndex;
    }
    
    static public final WeaponT ONE_HAND_AXE    = new WeaponT(  0, "One-hand Axe", "Axe", 1, 3);
    static public final WeaponT TWO_HAND_AXE    = new WeaponT(  1, "Two-hand Axe", "Axe", 2, 0);
    static public final WeaponT BOW             = new WeaponT(  2, "Bow", null, 2, 0);
    static public final WeaponT GUN             = new WeaponT(  3, "Gun", null, 2, 0);
    static public final WeaponT ONE_HAND_MACE   = new WeaponT(  4, "One-hand Mace", "Mace", 1, 3);
    static public final WeaponT TWO_HAND_MACE   = new WeaponT(  5, "Two-hand Mace", "Mace", 2, 0);
    static public final WeaponT POLEARM         = new WeaponT(  6, "Polearm", null, 2, 0);
    static public final WeaponT ONE_HAND_SWORD  = new WeaponT(  7, "One-hand Sword", "Sword", 1, 3);
    static public final WeaponT TWO_HAND_SWORD  = new WeaponT(  8, "Two-hand Sword", "Sword", 2, 0);
    static public final WeaponT STAFF           = new WeaponT( 10, "Staff", null, 2, 0);
    static public final WeaponT FIST            = new WeaponT( 13, "Fist", null, 1, 3);
    static public final WeaponT MISC            = new WeaponT( 14, "Miscellaneous", "", 0, 3);
    static public final WeaponT DAGGER          = new WeaponT( 15, "Dagger", null, 1, 3);
    static public final WeaponT THROWN          = new WeaponT( 16, "Thrown", null, 1, 4);
    static public final WeaponT XBOW            = new WeaponT( 18, "Crossbow", null, 2, 0);
    static public final WeaponT WAND            = new WeaponT( 19, "Wand", null, 1, 3);
    static public final WeaponT FISHING_POLE    = new WeaponT( 20, "Fishing Pole", null, 2, 3);
    
    static public final BitContainer<WeaponT> db = new BitContainer(WeaponT.class);   
    static public final BlizzBit<WeaponT> blizzBits = new BlizzBit<>(WeaponT.class, x -> 1 << x.id); 
    
    static public final long TRIVIAL = db.encode(FISHING_POLE, MISC);    
    static public final long TITANS_GRIP = db.encode(TWO_HAND_AXE, TWO_HAND_MACE, TWO_HAND_SWORD, STAFF, POLEARM);

}
