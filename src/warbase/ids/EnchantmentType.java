package warbase.ids;

public class EnchantmentType {

    /*
    ITEM_ENCHANTMENT_NONE             = 0,
    ITEM_ENCHANTMENT_COMBAT_SPELL     = 1,
    ITEM_ENCHANTMENT_DAMAGE           = 2,
    ITEM_ENCHANTMENT_EQUIP_SPELL      = 3,
    ITEM_ENCHANTMENT_RESISTANCE       = 4,
    ITEM_ENCHANTMENT_STAT             = 5,
    ITEM_ENCHANTMENT_TOTEM            = 6,
    ITEM_ENCHANTMENT_USE_SPELL        = 7,
    ITEM_ENCHANTMENT_PRISMATIC_SOCKET = 8
    */
    
    static public final int COMBAT_SPELL = 1;
    static public final int DAMAGE = 2; // 2 = old stuff, +Melee Damage, +Armor   
    static public final int EQUIP_SPELL = 3;  
    static public final int RESIST = 4; // 4 = wacko stuff, +4 Agility per different colored gem
    static public final int STAT = 5;
    static public final int TOTEM = 6;
    static public final int USE_SPELL = 7;
    
    // fake
    static public final int SKILL = 20;
    static public final int MOVEMENT_SPEED = 21;
    
}
