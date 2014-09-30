package warbase.types;

public class SpecRoleT extends TypeT {
    
    public final boolean spirit;
    public final boolean bonusArmor;

    private SpecRoleT(int id, String name, boolean spirit, boolean bonusArmor) {
        super(id, id, name);
        this.spirit = spirit;
        this.bonusArmor = bonusArmor;
    }

    static public final SpecRoleT TANK      = new SpecRoleT(0, "Tank",   false, true);
    static public final SpecRoleT HEAL      = new SpecRoleT(1, "Heal",   true,  false);
    static public final SpecRoleT DAMAGE    = new SpecRoleT(2, "Damage", false, false);
    
    static public final SpecRoleT[] types = indexed(SpecRoleT.class);    
    
}
