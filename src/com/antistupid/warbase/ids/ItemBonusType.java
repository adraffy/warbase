package com.antistupid.warbase.ids;

public class ItemBonusType {

    /*
    BonusIDs are stored in ItemBonus.db2.
    BonusIDs have a massive number of uses, including but not limited to:
    Random suffixes with varying stat amounts - ex. of the Fireflash: BonusIDs 19-39
    Random bonus tertiary stats - BonusID 40: +Avoidance, BonusID 41: +Leech, etc
    Raid typing - see above
    Dungeon typing - i.e. BonusID 518 with qualifier 17 restricts Bloodmaul Slag Mines Normal mode items to have -10 level
    Random quest upgrades - see above entries for 'Rare' and 'Epic'
    Crafting upgrades - BonusID 525: Basic, BonusID 526: Expert, etc
    BonusIDs can do the following 10 actions:
    1 - Adjust Itemlevel
    2 - Modify Stats
    3 - Change Item Quality
    4 - Add Item Titles
    5 - Append Words to Item Name
    6 - Add Sockets
    7 - Adjust ItemAppearanceID (which controls ItemDisplayInfoID and FileDataIconID)
    8 - Adjust Equip Level
    9 - Unknown
    10 - Unknown
    */
    
    /*
    wardbc.lazy.rows.ItemBonus
        id = 1512
    nodeId = 566
      type = 10
      val1 = 125
      val2 = 0
     index = 2
    */
    
    static public final int ITEM_LEVEL_DELTA = 1; // val1 = raw id
    static public final int STAT_ALLOC = 2; // stat only?
    static public final int QUALITY = 3;
    static public final int NAME_DESC = 4; // val1 = raw id
    static public final int NAME_SUFFIX = 5; 
    static public final int SOCKET = 6; // val1 = socket number, val2 == color (4 = yellow)
    static public final int APPEARANCE = 7; // not sure how to interpret
    static public final int REQ_LEVEL_DELTA = 8;
    static public final int UNKNOWN_9 = 9;
    static public final int UNKNOWN_10 = 10; // 125,200 
    
    //497:[
    //id(966) nodeId(497) type(5) val1(13162) val2(1) index(0), --> name desc
    //id(967) nodeId(497) type(1) val1(50) val2(0) index(1),    --> item level delta = 50? wot
    //id(970) nodeId(497) type(6) val1(1) val2(8) index(2),     --> socket
    //id(974) nodeId(497) type(7) val1(1) val2(2) index(3)]     --> appear

    
}
