package warbase.types;

import java.util.function.Consumer;

public class ProfT extends TypeT {
    
    public final String tinyName;

    static private int _index;
    public ProfT(int id, String name, String tinyName) {
        super(_index++, id, name);
        this.tinyName = tinyName;
    }
    
    @Override
    void registerNames(Consumer<String> c) {
        c.accept(tinyName);
    }

    static public final ProfT ENCH      = new ProfT(333, "Enchanting",      "Ench");
    static public final ProfT ENG       = new ProfT(202, "Engineering",     "Eng");
    static public final ProfT JC        = new ProfT(755, "Jewelcrafting",   "JC");
    static public final ProfT TAILOR    = new ProfT(197, "Tailoring",       "Tailor");
    static public final ProfT INS       = new ProfT(773, "Inscription",     "Ins");
    static public final ProfT BS        = new ProfT(164, "Blacksmithing",   "BS");
    static public final ProfT LW        = new ProfT(165, "Leatherworking",  "LW");
    static public final ProfT SKIN      = new ProfT(393, "Skinning",        "Skin");
    static public final ProfT ALCH      = new ProfT(171, "Alchemy",         "Alch");
    static public final ProfT HERB      = new ProfT(182, "Herbalism",       "Herb");
    static public final ProfT MINE      = new ProfT(186, "Mining",          "Mine");
    
    static public final ProfT FISH      = new ProfT(356, "Fishing",         "Fish");
        
    static public final BitContainer<ProfT> db = new BitContainer<>(ProfT.class);    
    static public final TypeNames<ProfT> names = new TypeNames<>(db.types);
    
}
