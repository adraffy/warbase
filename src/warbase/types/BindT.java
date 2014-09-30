package warbase.types;

public class BindT extends TypeT {

    public final String shortName;
    
    public BindT(int index, int id, String name, String shortName) {
        super(index, id, name);
        this.shortName = shortName;
    }
    
    static public class Blizz {
        static public final int NONE       = 0;
        static public final int ON_USE     = 1;
        static public final int ON_EQUIP   = 2;
        static public final int ON_LOOT    = 3;
        static public final int QUEST_ITEM = 4; 
        //static public final int QUEST_ITEM_2 = 5; 
    }
    
    static public final BindT NONE  = new BindT(0, Blizz.NONE,         "None",                 "None");    
    static public final BindT BOU   = new BindT(1, Blizz.ON_USE,       "Binds when used",      "Use");
    static public final BindT BOE   = new BindT(2, Blizz.ON_EQUIP,     "Binds when equipped",  "Equip");
    static public final BindT BOP   = new BindT(3, Blizz.ON_LOOT,      "Binds when picked up", "Loot");
    static public final BindT QUEST = new BindT(4, Blizz.QUEST_ITEM,   "Quest Item",           "Quest");  
    static public final BindT BOA   = new BindT(5, 6,                  "Binds to account",     "Account");
    
    static public final Assoc<BindT> ids = assoc(BindT.class);   
    
}
