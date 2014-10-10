package com.antistupid.warbase.ids;

public class ItemClass {
    
    /*
id(0) weapon(false) flags(0.25) name(Consumable)
id(1) weapon(false) flags(0.25) name(Container)
id(2) weapon(true) flags(0.2) name(Weapon)
id(3) weapon(false) flags(0.25) name(Gem)
id(4) weapon(false) flags(0.2) name(Armor)
id(5) weapon(false) flags(0.25) name(Reagent)
id(6) weapon(false) flags(0.25) name(Projectile)
id(7) weapon(false) flags(0.25) name(Trade Goods)
id(8) weapon(false) flags(0.25) name(Generic(OBSOLETE))
id(9) weapon(false) flags(0.25) name(Recipe)
id(10) weapon(false) flags(0.25) name(Money(OBSOLETE))
id(11) weapon(false) flags(0.25) name(Quiver)
id(12) weapon(false) flags(0.25) name(Quest)
id(13) weapon(false) flags(0.25) name(Key)
id(14) weapon(false) flags(0.25) name(Permanent(OBSOLETE))
id(15) weapon(false) flags(0.25) name(Miscellaneous)
id(16) weapon(false) flags(0.25) name(Glyph)
id(17) weapon(false) flags(0.25) name(Battle Pets)
    */
    
    static public final int CONSUMABLE = 0;
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
