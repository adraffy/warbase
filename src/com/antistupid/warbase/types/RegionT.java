package com.antistupid.warbase.types;

public class RegionT extends TypeT {

    public final String shortName;
    public final String apiURLPrefix;
    public final String wwwURLPrefix;
    public final boolean asia;
    
    public RegionT(int id, String name, String shortName, String apiPrefix, String wwwPrefix, boolean asia) {
        super(id, id, name);
        this.shortName = shortName;
        this.apiURLPrefix = apiPrefix;
        this.wwwURLPrefix = wwwPrefix;
        this.asia = asia;
    }
    
    static public RegionT US = new RegionT(0, "US",     "US", "https://us.api.battle.net/",     "http://us.battle.net/", false);
    static public RegionT EU = new RegionT(1, "Europe", "EU", "https://eu.api.battle.net/",     "http://eu.battle.net/", false);
    static public RegionT TW = new RegionT(2, "Taiwan", "TW", "https://tw.api.battle.net/",     "http://tw.battle.net/", true);
    static public RegionT KR = new RegionT(3, "Korea",  "KR", "https://kr.api.battle.net/",     "http://kr.battle.net/", true);
    static public RegionT CN = new RegionT(4, "China",  "CN", "https://www.battlenet.com.cn/",  "http://www.battlenet.com.cn/", true);
   
    static public final BitContainer<RegionT> db = new BitContainer<>(RegionT.class);
    static public final TypeNames<RegionT> names = new TypeNames<>(db.types);
    
    
    
}
