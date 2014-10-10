package com.antistupid.warbase.types;

public class CombatRoleT extends TypeT{
    
    private CombatRoleT(int id, String name) { //, SpecRoleT role) {
        super(id, id, name);
    }
    
    static public final CombatRoleT TANK        = new CombatRoleT(0, "Tank"); //,    SpecRoleT.TANK);
    static public final CombatRoleT HEAL        = new CombatRoleT(1, "Heal"); //,    SpecRoleT.HEAL);
    static public final CombatRoleT DPS_MELEE   = new CombatRoleT(2, "Melee"); //,   SpecRoleT.DAMAGE);
    static public final CombatRoleT DPS_RANGE   = new CombatRoleT(3, "Ranged"); //,  SpecRoleT.DAMAGE);

    static public final Container<CombatRoleT> db = new Container(CombatRoleT.class);
    
}
