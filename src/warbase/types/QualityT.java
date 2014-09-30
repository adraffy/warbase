package warbase.types;

public class QualityT extends TypeT {

    public final double armorMod;
    public final int randPropArrayIndex;
    public final int damageIndex;
    
    public QualityT(int id, String name, double armorMod, int randPropArrayIndex, int damageIndex) {
        super(id, id, name);
        this.armorMod = armorMod;
        this.randPropArrayIndex = randPropArrayIndex;
        this.damageIndex = damageIndex;
    }

    static public final QualityT POOR       = new QualityT(0, "Poor",       0.90,   -1, 0);
    static public final QualityT COMMON     = new QualityT(1, "Common",     0.95,   -1, 1);
    static public final QualityT GREEN      = new QualityT(2, "Uncommon",   1.00,    2, 2);
    static public final QualityT BLUE       = new QualityT(3, "Rare",       1.00,    1, 3);
    static public final QualityT PURPLE     = new QualityT(4, "Epic",       1.00,    0, 4);
    static public final QualityT ORANGE     = new QualityT(5, "Legendary",  1.00,    0, 4);
    static public final QualityT ARTIFACT   = new QualityT(6, "Artifact",   1.00,    0, 4);
    static public final QualityT HEIRLOOM   = new QualityT(7, "Heirloom",   1.00,    1, 3);
    
    static public final BitContainer<QualityT> db = new BitContainer<>(QualityT.class);
        
    static public QualityT max(QualityT a, QualityT b) {
        return a == null ? b : b == null ? a : a.id > b.id ? a : b;
    }
    
}
