package com.antistupid.warbase.types;

import com.antistupid.warbase.ids.ItemClass;

public class ConsumeT extends TypeT {
    
    public final boolean isElixirFlaskScroll; // battle/guardian category
    
    private ConsumeT(int id, String name, boolean efs) {
        super(id - 1, id, name);
        this.isElixirFlaskScroll = efs;
    }

    static public final ConsumeT POTION = new ConsumeT(ItemClass.Consumable.POTION, "Potion",   false);
    static public final ConsumeT ELIXIR = new ConsumeT(ItemClass.Consumable.ELIXIR, "Elixir",   true);
    static public final ConsumeT FLASK  = new ConsumeT(ItemClass.Consumable.FLASK,  "Flask",    true);
    static public final ConsumeT SCROLL = new ConsumeT(ItemClass.Consumable.SCROLL, "Scroll",   true);
    static public final ConsumeT FOOD   = new ConsumeT(ItemClass.Consumable.FOOD,   "Food",     false);
    
    static public final BitContainer<ConsumeT> db = new BitContainer<>(ConsumeT.class);

    
}
