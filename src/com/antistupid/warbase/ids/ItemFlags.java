package com.antistupid.warbase.ids;

public class ItemFlags {

    static public class F1 {        
        static public final int HEROIC              = 0x00000008;
        static public final int UNIQUE_EQUIPPED     = 0x00080000;
        static public final int USEABLE_IN_ARENA    = 0x00200000;
        static public final int BIND_TO_ACCOUNT     = 0x08000000;
        
    }
    
    static public class F2 {
        static public final int NOT_UPGRADEABLE     = 0x00000002;
        static public final int CASTER_WEAPON       = 0x00000200;
    }
    
    
    /*
    
enum item_flag
{
    ITEM_FLAG_UNK0                            = 0x00000001, // not used
    ITEM_FLAG_CONJURED                        = 0x00000002,
    ITEM_FLAG_LOOTABLE                        = 0x00000004, // affect only non container items that can be "open" for loot. It or lockid set enable for client show "Right click to open". See also ITEM_DYNFLAG_UNLOCKED
    ITEM_FLAG_HEROIC                          = 0x00000008, // heroic item version
    ITEM_FLAG_UNK4                            = 0x00000010, // can't repeat old note: appears red icon (like when item durability==0)
    ITEM_FLAG_INDESTRUCTIBLE                  = 0x00000020, // used for totem. Item can not be destroyed, except by using spell (item can be reagent for spell and then allowed)
    ITEM_FLAG_UNK6                            = 0x00000040, // ? old note: usable
    ITEM_FLAG_NO_EQUIP_COOLDOWN               = 0x00000080,
    ITEM_FLAG_UNK8                            = 0x00000100, // saw this on item 47115, 49295...
    ITEM_FLAG_WRAPPER                         = 0x00000200, // used or not used wrapper
    ITEM_FLAG_IGNORE_BAG_SPACE                = 0x00000400, // ignore bag space at new item creation?
    ITEM_FLAG_PARTY_LOOT                      = 0x00000800, // determines if item is party loot or not
    ITEM_FLAG_REFUNDABLE                      = 0x00001000, // item cost can be refunded within 2 hours after purchase
    ITEM_FLAG_CHARTER                         = 0x00002000, // arena/guild charter
    ITEM_FLAG_UNK14                           = 0x00004000,
    ITEM_FLAG_UNK15                           = 0x00008000, // a lot of items have this
    ITEM_FLAG_UNK16                           = 0x00010000, // a lot of items have this
    ITEM_FLAG_UNK17                           = 0x00020000,
    ITEM_FLAG_PROSPECTABLE                    = 0x00040000, // item can have prospecting loot (in fact some items expected have empty loot)
    ITEM_FLAG_UNIQUE_EQUIPPED                 = 0x00080000,
    ITEM_FLAG_UNK20                           = 0x00100000,
    ITEM_FLAG_USEABLE_IN_ARENA                = 0x00200000,
    ITEM_FLAG_THROWABLE                       = 0x00400000, // Only items of ITEM_SUBCLASS_WEAPON_THROWN have it but not all, so can't be used as in game check
    ITEM_FLAG_SPECIALUSE                      = 0x00800000, // last used flag in 2.3.0
    ITEM_FLAG_UNK24                           = 0x01000000,
    ITEM_FLAG_UNK25                           = 0x02000000,
    ITEM_FLAG_UNK26                           = 0x04000000,
    ITEM_FLAG_BOA                             = 0x08000000, // bind on account (set in template for items that can binded in like way)
    ITEM_FLAG_ENCHANT_SCROLL                  = 0x10000000, // for enchant scrolls
    ITEM_FLAG_MILLABLE                        = 0x20000000, // item can have milling loot
    ITEM_FLAG_UNK30                           = 0x04000000,
    ITEM_FLAG_BOP_TRADEABLE                   = 0x80000000, // bound item that can be traded
};

enum item_flag2
{
    ITEM_FLAG2_HORDE_ONLY                     = 0x00000001, // drop in loot, sell by vendor and equipping only for horde
    ITEM_FLAG2_ALLIANCE_ONLY                  = 0x00000002, // drop in loot, sell by vendor and equipping only for alliance
    ITEM_FLAG2_EXT_COST_REQUIRES_GOLD         = 0x00000004, // item cost include gold part in case extended cost use also
    ITEM_FLAG2_UNK4                           = 0x00000008,
    ITEM_FLAG2_UNK5                           = 0x00000010,
    ITEM_FLAG2_UNK6                           = 0x00000020,
    ITEM_FLAG2_UNK7                           = 0x00000040,
    ITEM_FLAG2_UNK8                           = 0x00000080,
    ITEM_FLAG2_NEED_ROLL_DISABLED             = 0x00000100, // need roll during looting is not allowed for this item
    ITEM_FLAG2_CASTER_WEAPON                  = 0x00000200, // uses caster specific dbc file for DPS calculations
    ITEM_FLAG2_HEROIC                         = 0x40000000, // MoP Heroic item flag
};
    */
    
}
