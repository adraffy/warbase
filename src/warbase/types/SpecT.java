package warbase.types;

public class SpecT extends TypeT {
    
    public final int specIndex;
    public final ClassT classType;
    public final ArmorT armorType;
    public final StatT primaryStat; // specialization, kicks in at level 50
    public final StatT attuneRating; // attunement, kicks in at level 90
    public final boolean canDualWield;
    public final SpecRoleT role;
   
    static private int _index;
    public SpecT(int id, int specIndex, String name, ClassT classType, ArmorT armorType, StatT primaryStat, StatT attuneRating, boolean canDualWield, SpecRoleT role) {
        super(_index++, id, name);
        this.specIndex = specIndex;        
        this.classType = classType;
        this.armorType = armorType;
        this.primaryStat = primaryStat;
        this.attuneRating = attuneRating;
        this.canDualWield = canDualWield;
        this.role = role;
    }
    
    
    
        
    static public final SpecT BLOOD        = new SpecT(250, 0, "Blood",           ClassT.DK,     ArmorT.PLATE, StatT.STR, StatT.MULTI, true, SpecRoleT.TANK);
    static public final SpecT FROST_DK     = new SpecT(251, 1, "Frost",           ClassT.DK,     ArmorT.PLATE, StatT.STR, StatT.HASTE, true, SpecRoleT.DAMAGE);
    static public final SpecT UNHOLY       = new SpecT(252, 2, "Unholy",          ClassT.DK,     ArmorT.PLATE, StatT.STR, StatT.MULTI, true, SpecRoleT.DAMAGE); 

    static public final SpecT BALANCE      = new SpecT(102, 0, "Balance",         ClassT.DRUID,  ArmorT.LEATHER, StatT.INT, StatT.MASTERY,  false, SpecRoleT.DAMAGE);
    static public final SpecT FERAL        = new SpecT(103, 1, "Feral",           ClassT.DRUID,  ArmorT.LEATHER, StatT.AGI, StatT.CRIT,     false, SpecRoleT.DAMAGE);   
    static public final SpecT GUARDIAN     = new SpecT(104, 2, "Guardian",        ClassT.DRUID,  ArmorT.LEATHER, StatT.AGI, StatT.MASTERY,  false, SpecRoleT.TANK);
    static public final SpecT RESTO_DRUID  = new SpecT(105, 3, "Restoration",     ClassT.DRUID,  ArmorT.LEATHER, StatT.INT, StatT.HASTE,    false, SpecRoleT.HEAL);
        
    static public final SpecT BM           = new SpecT(253, 0, "Beastmaster",     ClassT.HUNTER, ArmorT.MAIL, StatT.AGI, StatT.MASTERY, true, SpecRoleT.DAMAGE);
    static public final SpecT MARKS        = new SpecT(254, 1, "Marksmenship",    ClassT.HUNTER, ArmorT.MAIL, StatT.AGI, StatT.CRIT,    true, SpecRoleT.DAMAGE);    
    static public final SpecT SURVIVAL     = new SpecT(255, 2, "Survival",        ClassT.HUNTER, ArmorT.MAIL, StatT.AGI, StatT.MULTI,   true, SpecRoleT.DAMAGE);
        
    static public final SpecT BREW         = new SpecT(268, 0, "Brewmaster",      ClassT.MONK, ArmorT.LEATHER, StatT.AGI, StatT.CRIT,   true, SpecRoleT.DAMAGE);
    static public final SpecT MIST         = new SpecT(270, 1, "Mistweaver",      ClassT.MONK, ArmorT.LEATHER, StatT.INT, StatT.MULTI,  false, SpecRoleT.HEAL);
    static public final SpecT WIND         = new SpecT(269, 2, "Windwalker",      ClassT.MONK, ArmorT.LEATHER, StatT.AGI, StatT.MULTI,  true, SpecRoleT.TANK);
  
    static public final SpecT ARCANE       = new SpecT( 62, 0, "Arcane",          ClassT.MAGE, ArmorT.CLOTH, StatT.INT, StatT.MASTERY, false, SpecRoleT.DAMAGE);
    static public final SpecT FIRE         = new SpecT( 63, 1, "Fire",            ClassT.MAGE, ArmorT.CLOTH, StatT.INT, StatT.CRIT, false, SpecRoleT.DAMAGE);
    static public final SpecT FROST_MAGE   = new SpecT( 64, 2, "Frost",           ClassT.MAGE, ArmorT.CLOTH, StatT.INT, StatT.MULTI, false, SpecRoleT.DAMAGE);
    
    static public final SpecT HOLY_PALLY   = new SpecT( 65, 0, "Holy",            ClassT.PALADIN, ArmorT.PLATE, StatT.INT, StatT.CRIT, false, SpecRoleT.HEAL);
    static public final SpecT PROT_PALLY   = new SpecT( 66, 1, "Protection",      ClassT.PALADIN, ArmorT.PLATE, StatT.STR, StatT.HASTE, false, SpecRoleT.TANK);
    static public final SpecT RET          = new SpecT( 70, 2, "Retribution",     ClassT.PALADIN, ArmorT.PLATE, StatT.STR, StatT.MASTERY, false, SpecRoleT.DAMAGE);   
            
    static public final SpecT DISC         = new SpecT(256, 0, "Discipline",      ClassT.PRIEST, ArmorT.CLOTH, StatT.INT, StatT.CRIT, false, SpecRoleT.HEAL);
    static public final SpecT HOLY_PRIEST  = new SpecT(257, 1, "Holy",            ClassT.PRIEST, ArmorT.CLOTH, StatT.INT, StatT.MULTI, false, SpecRoleT.HEAL);
    static public final SpecT SHADOW       = new SpecT(258, 2, "Shadow",          ClassT.PRIEST, ArmorT.CLOTH, StatT.INT, StatT.HASTE, false, SpecRoleT.DAMAGE);   

    static public final SpecT ASS          = new SpecT(259, 0, "Assassination",   ClassT.ROGUE, ArmorT.LEATHER, StatT.AGI, StatT.MASTERY, true, SpecRoleT.DAMAGE);
    static public final SpecT COMBAT       = new SpecT(260, 1, "Combat",          ClassT.ROGUE, ArmorT.LEATHER, StatT.AGI, StatT.HASTE, true, SpecRoleT.DAMAGE);
    static public final SpecT SUB          = new SpecT(261, 2, "Subtlety",        ClassT.ROGUE, ArmorT.LEATHER, StatT.AGI, StatT.MULTI, true, SpecRoleT.DAMAGE);   

    static public final SpecT ELE          = new SpecT(262, 0, "Elemental",       ClassT.SHAMAN, ArmorT.MAIL, StatT.INT, StatT.MULTI, false, SpecRoleT.DAMAGE);
    static public final SpecT ENH          = new SpecT(263, 1, "Enhancement",     ClassT.SHAMAN, ArmorT.MAIL, StatT.AGI, StatT.HASTE, true, SpecRoleT.DAMAGE);
    static public final SpecT RESTO_SHAMAN = new SpecT(264, 2, "Restoration",     ClassT.SHAMAN, ArmorT.MAIL, StatT.INT, StatT.MASTERY, false, SpecRoleT.HEAL);   

    static public final SpecT AFF          = new SpecT(265, 0, "Affliction",      ClassT.WARLOCK, ArmorT.CLOTH, StatT.INT, StatT.HASTE, false, SpecRoleT.DAMAGE);
    static public final SpecT DEMON        = new SpecT(266, 1, "Demonology",      ClassT.WARLOCK, ArmorT.CLOTH, StatT.INT, StatT.MASTERY, false, SpecRoleT.DAMAGE);
    static public final SpecT DEST         = new SpecT(267, 2, "Destruction",     ClassT.WARLOCK, ArmorT.CLOTH, StatT.INT, StatT.CRIT, false, SpecRoleT.DAMAGE);   

    static public final SpecT ARMS         = new SpecT( 71, 0, "Arms",            ClassT.WARRIOR, ArmorT.PLATE, StatT.STR, StatT.MASTERY, true, SpecRoleT.DAMAGE);
    static public final SpecT FURY         = new SpecT( 72, 1, "Fury",            ClassT.WARRIOR, ArmorT.PLATE, StatT.STR, StatT.CRIT, true, SpecRoleT.DAMAGE);
    static public final SpecT PROT         = new SpecT( 73, 2, "Protection",      ClassT.WARRIOR, ArmorT.PLATE, StatT.STR, StatT.MASTERY, true, SpecRoleT.TANK); 
        
    
    static public final Container<SpecT> db = new Container<>(SpecT.class);
    static public final TypeNames<SpecT> names = new TypeNames<>(db.types, true);

    static {
        for (SpecT x: SpecT.db.types) {
            x.classType.specs.add(x);            
        }
    }
        
    /*

    static class ClassPower {
        final ClassT cls;
        final TypeSet<SpecT> specs;
        final TypeIntBitSet<SpecRoleT> roles;
        ClassPower(ClassT cls, TypeSet<SpecT> specs, TypeIntBitSet<SpecRoleT> roles) {
            this.cls = cls;
            this.specs = specs;
            this.roles = roles;
        }
    }
    
    
    static public final Comparator<SpecT> CMP_ID_THEN_INDEX = (a, b) -> {
        int c = Integer.compare(a.id, b.id);
        return c == 0 ? Integer.compare(a.specIndex, b.specIndex) : c;
    };
    
    static private final TreeMap<ClassT,TypeSet<SpecT>> _classToSpecMap = new TreeMap<>(CMP_ID);
    static {
        TreeMap<ClassT,ArrayList<SpecT>> map = new TreeMap<>(CMP_ID);
        for (SpecT x: db.types) {
            ArrayList<SpecT> list = map.get(x.classType);
            if (list == null) {
                list = new ArrayList<>();
                map.put(x.classType, list);
            }
            list.add(x);
        }        
        map.forEach((k, v) -> {
            v.sort(CMP_ID_THEN_INDEX);
            _classToSpecMap.put(k, set(v)); //, SpecRoleT.db.set(v.stream().mapToInt(x -> x.role.bit).reduce(0, (a, b) -> a | b))));
        });
    }
    
    static public TypeSet<SpecT> forClass(ClassT cls) {
        return _classToSpecMap.get(cls); //.specs;
    }    
    */
    

}
