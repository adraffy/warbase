package com.antistupid.warbase.types;

import java.util.function.Consumer;

public class ProfT extends TypeT {
    
    public final String tinyName;
    public final String iconName;
    public final boolean primary;

    static private int _index;
    public ProfT(int id, String name, String tinyName, String iconName, boolean primary) {
        super(_index++, id, name);
        this.tinyName = tinyName;
        this.iconName = iconName;
        this.primary = primary;
    }
    
    @Override
    void registerNames(Consumer<String> c) {
        c.accept(tinyName);
    }

    static public final ProfT ENCH      = new ProfT(333, "Enchanting",      "Ench", "trade_engraving", true);
    static public final ProfT ENG       = new ProfT(202, "Engineering",     "Eng", "trade_engineering", true);
    static public final ProfT JC        = new ProfT(755, "Jewelcrafting",   "JC", "inv_misc_gem_01", true);
    static public final ProfT TAILOR    = new ProfT(197, "Tailoring",       "Tailor", "trade_tailoring", true);
    static public final ProfT INS       = new ProfT(773, "Inscription",     "Ins", "inv_inscription_tradeskill01", true);
    static public final ProfT BS        = new ProfT(164, "Blacksmithing",   "BS", "trade_blacksmithing", true);
    static public final ProfT LW        = new ProfT(165, "Leatherworking",  "LW", "inv_misc_armorkit_17", true);
    static public final ProfT SKIN      = new ProfT(393, "Skinning",        "Skin", "inv_misc_pelt_wolf_01", true);
    static public final ProfT ALCH      = new ProfT(171, "Alchemy",         "Alch", "trade_alchemy", true);
    static public final ProfT HERB      = new ProfT(182, "Herbalism",       "Herb", "spell_nature_naturetouchgrow", true);
    static public final ProfT MINE      = new ProfT(186, "Mining",          "Mine", "trade_mining", true);
    
    static public final ProfT FISH      = new ProfT(356, "Fishing",         "Fish", "trade_fishing", false);
    static public final ProfT FIRST_AID = new ProfT(129, "First Aid",       "Aid", "spell_holy_sealofsacrifice", false);
    static public final ProfT COOKING   = new ProfT(185, "Cooking",         "Cook", "inv_misc_food_15", false);
    static public final ProfT ARCH      = new ProfT(794, "Archaeology",     "Arch", "trade_archaeology", false);
        
    static public final BitContainer<ProfT> db = new BitContainer<>(ProfT.class);    
    static public final TypeNames<ProfT> names = new TypeNames<>(db.types);
    
    static public final ProfT[] PRIMARY = db.selectArray(x -> x.primary);
    static public final ProfT[] SECONDARY = db.selectArray(x -> !x.primary);
    
}
