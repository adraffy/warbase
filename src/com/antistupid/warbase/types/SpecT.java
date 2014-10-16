package com.antistupid.warbase.types;

public class SpecT extends TypeT {
        
    static public final int PLAYER_LEVEL_ARMOR_SPECIALIZATION = 50;
    static public final int PLAYER_LEVEL_MASTERY = 80;
    static public final int PLAYER_LEVEL_ATTUNE_RATING = 90;
    
    static public final double ARMOR_SPECIALIZATION_COEFF = 1.05;
    static public final double ATTUNE_RATING_COEFF = 1.05;
    
    public final int specIndex;
    public final ClassT classType;
    public final String fullName;
    public final ArmorT armorType;
    public final StatT primaryStat; // specialization, kicks in at level 50
    public final RatingT attuneRating; // attunement, kicks in at level 90
    public final boolean canDualWield;
    public final SpecRoleT role;
    public final String iconName;
    public final boolean manaHybrid;
    public final double masteryCoeff;
   
    static private int _index;
    public SpecT(int id, int specIndex, String name, ClassT classType, ArmorT armorType, StatT primaryStat, 
            RatingT attuneRating, boolean manaHybrid, boolean canDualWield, SpecRoleT role, String iconName, double masteryCoeff) {
        super(_index++, id, name);
        this.specIndex = specIndex;        
        this.classType = classType;
        this.armorType = armorType;
        this.primaryStat = primaryStat;
        this.attuneRating = attuneRating;
        this.manaHybrid = manaHybrid;
        this.canDualWield = canDualWield;
        this.role = role;
        this.iconName = iconName;
        this.masteryCoeff = masteryCoeff;
        fullName = name + " " + classType.name;
    }
        
    public boolean hasAttackPowerMasteryBonus() {
        return role == SpecRoleT.TANK;
    }
    
    public boolean hasCritialStrikes() {
        return primaryStat == StatT.AGI; // hunter, rogue, feral/guard, enh, wind/brew
    }
        
    static public final SpecT BLOOD        = new SpecT(250, 0, "Blood",           ClassT.DK,     ArmorT.PLATE, StatT.STR, RatingT.MULTI, false, true, SpecRoleT.TANK, "spell_deathknight_bloodpresence", 2);
    static public final SpecT FROST_DK     = new SpecT(251, 1, "Frost",           ClassT.DK,     ArmorT.PLATE, StatT.STR, RatingT.HASTE, false, true, SpecRoleT.DAMAGE, "spell_deathknight_frostpresence", 2);
    static public final SpecT UNHOLY       = new SpecT(252, 2, "Unholy",          ClassT.DK,     ArmorT.PLATE, StatT.STR, RatingT.MULTI, false, true, SpecRoleT.DAMAGE, "spell_deathknight_unholypresence", 2.5); 

    static public final SpecT BALANCE      = new SpecT(102, 0, "Balance",         ClassT.DRUID,  ArmorT.LEATHER, StatT.INT, RatingT.MASTERY,  false, false, SpecRoleT.DAMAGE, "spell_nature_starfall", 1.87);
    static public final SpecT FERAL        = new SpecT(103, 1, "Feral",           ClassT.DRUID,  ArmorT.LEATHER, StatT.AGI, RatingT.CRIT,     true, false, SpecRoleT.DAMAGE, "ability_druid_catform", 3.13);   
    static public final SpecT GUARDIAN     = new SpecT(104, 2, "Guardian",        ClassT.DRUID,  ArmorT.LEATHER, StatT.AGI, RatingT.MASTERY,  true, false, SpecRoleT.TANK, "ability_racial_bearform", 1.5);
    static public final SpecT RESTO_DRUID  = new SpecT(105, 3, "Restoration",     ClassT.DRUID,  ArmorT.LEATHER, StatT.INT, RatingT.HASTE,    false, false, SpecRoleT.HEAL, "spell_nature_healingtouch", 1.25);
        
    static public final SpecT BM           = new SpecT(253, 0, "Beastmaster",     ClassT.HUNTER, ArmorT.MAIL, StatT.AGI, RatingT.MASTERY, false, true, SpecRoleT.DAMAGE, "ability_hunter_bestialdiscipline", 2);
    static public final SpecT MARKS        = new SpecT(254, 1, "Marksmenship",    ClassT.HUNTER, ArmorT.MAIL, StatT.AGI, RatingT.CRIT,    false, true, SpecRoleT.DAMAGE, "ability_hunter_focusedaim", 0.5);    
    static public final SpecT SURVIVAL     = new SpecT(255, 2, "Survival",        ClassT.HUNTER, ArmorT.MAIL, StatT.AGI, RatingT.MULTI,   false, true, SpecRoleT.DAMAGE, "ability_hunter_camouflage", 1);
        
    static public final SpecT BREW         = new SpecT(268, 0, "Brewmaster",      ClassT.MONK, ArmorT.LEATHER, StatT.AGI, RatingT.CRIT,   false, true, SpecRoleT.DAMAGE, "spell_monk_brewmaster_spec", 0.625);
    static public final SpecT MIST         = new SpecT(270, 1, "Mistweaver",      ClassT.MONK, ArmorT.LEATHER, StatT.INT, RatingT.MULTI,  false, false, SpecRoleT.HEAL, "spec_monk_mistweaver_spec", 0.5);
    static public final SpecT WIND         = new SpecT(269, 2, "Windwalker",      ClassT.MONK, ArmorT.LEATHER, StatT.AGI, RatingT.MULTI,  false, true, SpecRoleT.TANK, "spell_monk_windwalker_spec", 2.5);
  
    static public final SpecT ARCANE       = new SpecT( 62, 0, "Arcane",          ClassT.MAGE, ArmorT.CLOTH, StatT.INT, RatingT.MASTERY, false, false, SpecRoleT.DAMAGE, "spell_holy_magicalsentry", 1.50);
    static public final SpecT FIRE         = new SpecT( 63, 1, "Fire",            ClassT.MAGE, ArmorT.CLOTH, StatT.INT, RatingT.CRIT, false, false, SpecRoleT.DAMAGE, "spell_fire_firebolt02", 1.50);
    static public final SpecT FROST_MAGE   = new SpecT( 64, 2, "Frost",           ClassT.MAGE, ArmorT.CLOTH, StatT.INT, RatingT.MULTI, false, false, SpecRoleT.DAMAGE, "spell_frost_frostbolt02", 2);
    
    static public final SpecT HOLY_PALLY   = new SpecT( 65, 0, "Holy",            ClassT.PALADIN, ArmorT.PLATE, StatT.INT, RatingT.CRIT, false, false, SpecRoleT.HEAL, "spell_holy_holybolt", 1.25);
    static public final SpecT PROT_PALLY   = new SpecT( 66, 1, "Protection",      ClassT.PALADIN, ArmorT.PLATE, StatT.STR, RatingT.HASTE, true, false, SpecRoleT.TANK, "ability_paladin_shieldofthetemplar", 1.2);
    static public final SpecT RET          = new SpecT( 70, 2, "Retribution",     ClassT.PALADIN, ArmorT.PLATE, StatT.STR, RatingT.MASTERY, true, false, SpecRoleT.DAMAGE, "spell_holy_auraoflight", 2.1);   
            
    static public final SpecT DISC         = new SpecT(256, 0, "Discipline",      ClassT.PRIEST, ArmorT.CLOTH, StatT.INT, RatingT.CRIT, false, false, SpecRoleT.HEAL, "spell_holy_powerwordshield", 1.6);
    static public final SpecT HOLY_PRIEST  = new SpecT(257, 1, "Holy",            ClassT.PRIEST, ArmorT.CLOTH, StatT.INT, RatingT.MULTI, false, false, SpecRoleT.HEAL, "spell_holy_guardianspirit", 1.3);
    static public final SpecT SHADOW       = new SpecT(258, 2, "Shadow",          ClassT.PRIEST, ArmorT.CLOTH, StatT.INT, RatingT.HASTE, false, false, SpecRoleT.DAMAGE, "spell_shadow_shadowwordpain", 2.5);   

    static public final SpecT ASS          = new SpecT(259, 0, "Assassination",   ClassT.ROGUE, ArmorT.LEATHER, StatT.AGI, RatingT.MASTERY, false, true, SpecRoleT.DAMAGE, "ability_rogue_eviscerate", 3);
    static public final SpecT COMBAT       = new SpecT(260, 1, "Combat",          ClassT.ROGUE, ArmorT.LEATHER, StatT.AGI, RatingT.HASTE, false, true, SpecRoleT.DAMAGE, "ability_backstab", 2);
    static public final SpecT SUB          = new SpecT(261, 2, "Subtlety",        ClassT.ROGUE, ArmorT.LEATHER, StatT.AGI, RatingT.MULTI, false, true, SpecRoleT.DAMAGE, "ability_stealth", 3.5);   

    static public final SpecT ELE          = new SpecT(262, 0, "Elemental",       ClassT.SHAMAN, ArmorT.MAIL, StatT.INT, RatingT.MULTI, false, false, SpecRoleT.DAMAGE, "spell_nature_lightning", 4.5);
    static public final SpecT ENH          = new SpecT(263, 1, "Enhancement",     ClassT.SHAMAN, ArmorT.MAIL, StatT.AGI, RatingT.HASTE, true, true, SpecRoleT.DAMAGE, "spell_shaman_improvedstormstrike", 2.5);
    static public final SpecT RESTO_SHAMAN = new SpecT(264, 2, "Restoration",     ClassT.SHAMAN, ArmorT.MAIL, StatT.INT, RatingT.MASTERY, false, false, SpecRoleT.HEAL, "spell_nature_magicimmunity", 3);   

    static public final SpecT AFF          = new SpecT(265, 0, "Affliction",      ClassT.LOCK, ArmorT.CLOTH, StatT.INT, RatingT.HASTE, false, false, SpecRoleT.DAMAGE, "spell_shadow_deathcoil", 3.1);
    static public final SpecT DEMON        = new SpecT(266, 1, "Demonology",      ClassT.LOCK, ArmorT.CLOTH, StatT.INT, RatingT.MASTERY, false, false, SpecRoleT.DAMAGE, "spell_shadow_metamorphosis", 0.75);
    static public final SpecT DEST         = new SpecT(267, 2, "Destruction",     ClassT.LOCK, ArmorT.CLOTH, StatT.INT, RatingT.CRIT, false, false, SpecRoleT.DAMAGE, "spell_shadow_rainoffire", 3);   

    static public final SpecT ARMS         = new SpecT( 71, 0, "Arms",            ClassT.WAR, ArmorT.PLATE, StatT.STR, RatingT.MASTERY, false, true, SpecRoleT.DAMAGE, "ability_warrior_savageblow", 1.4);
    static public final SpecT FURY         = new SpecT( 72, 1, "Fury",            ClassT.WAR, ArmorT.PLATE, StatT.STR, RatingT.CRIT, false, true, SpecRoleT.DAMAGE, "ability_warrior_innerrage", 1.4);
    static public final SpecT PROT         = new SpecT( 73, 2, "Protection",      ClassT.WAR, ArmorT.PLATE, StatT.STR, RatingT.MASTERY, false, true, SpecRoleT.TANK, "ability_warrior_defensivestance", 1.5); 
        
    
    static public final Container<SpecT> db = new Container<>(SpecT.class);
    static public final TypeNames<SpecT> names = new TypeNames<>(db.types, true);

    static {
        for (SpecT x: SpecT.db.types) {
            x.classType.specs.add(x);            
        }
    }
    
    
}
