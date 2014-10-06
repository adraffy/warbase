package warbase.types;

import warbase.ids.ItemClass;

public class ItemClassT extends TypeT {

    public ItemClassT(int index, int id, String name) {
        super(index, id, name);
    }
    
    static public final ItemClassT WEAPON    = new ItemClassT(0, ItemClass.WEAPON, "Weapon");    
    static public final ItemClassT GEM       = new ItemClassT(1, ItemClass.GEM,    "Gem");
    static public final ItemClassT ARMOR     = new ItemClassT(2, ItemClass.ARMOR,  "Armor");
    static public final ItemClassT CONSUME   = new ItemClassT(3, ItemClass.ARMOR,  "Armor");
    
}
