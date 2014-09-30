package warbase.types;

public class RegionT extends TypeT {

    public final String host;
    public final boolean asia;
    
    public RegionT(int id, String name, String host, boolean asia) {
        super(id, id, name);
        this.host = host;
        this.asia = asia;
    }
    
    static public RegionT US = new RegionT(0, "US", "us.battle.net", false);
    static public RegionT EU = new RegionT(1, "EU", "eu.battle.net", false);
    static public RegionT TW = new RegionT(2, "TW", "tw.battle.net", true);
    static public RegionT KR = new RegionT(3, "KR", "kr.battle.net", true);
    static public RegionT CN = new RegionT(4, "CN", "www.battlenet.com.cn", true);
   
    static public final BitContainer<RegionT> db = new BitContainer<>(RegionT.class);
    static public final TypeNames<RegionT> names = new TypeNames<>(db.types);
    
}
