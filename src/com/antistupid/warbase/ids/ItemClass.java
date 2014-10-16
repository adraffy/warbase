package com.antistupid.warbase.ids;

public class ItemClass {
    
    static public final int CONSUMABLE = 0;
    static public class Consumable {
        static public final int POTION = 1;
        static public final int ELIXIR = 2;
        static public final int FLASK = 3;
        static public final int SCROLL = 4;
        static public final int FOOD = 5;
        static public final int ENCHANTMENT = 6;
        static public final int BANDAGE = 7;
    }
    static public final int CONTAINER = 1;
    static public final int WEAPON = 2;
    static public final int GEM = 3;
    static public final int ARMOR = 4;
    static public final int REAGENT = 5;
    static public final int PROJECTILE = 6;
    static public final int TRADE_GOODS = 7;
    static public final int BOOK = 9;
    static public final int QUIVER = 11;
    static public final int QUEST = 12;
    static public final int KEY = 13;
    static public final int MISCELLANEOUS = 15;
    static public final int GLYPH = 16;
    static public final int BATTLE_PETS = 17;

    static public boolean wearable(int id) {
        return id == WEAPON || id == ARMOR;
    }
    
}
