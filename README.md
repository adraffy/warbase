#WarBase
**A low-level World of Warcraft toolkit for Java**

*Warning: this document is a work-in-progress.*

####Random Examples
```java
// what specs can a druid be?
System.out.println(ClassT.DRUID.specs);
// [Feral, Resto, Balance, Guardian]

// what races can a druid be?
System.out.println(ClassT.DRUID.races);
// [Night Elf, Worgen, Tauren, Troll]

// what classes can tauren be?
System.out.println(RaceT.TAUREN.getRaces());
// [Druid]

// what is Feral's...
System.out.println(SpecT.FERAL.armorType); // armor specialization? 
// ArmorT.LEATHER
System.out.println(SpecT.FERAL.primaryStat); // primary stat?
// StatT.AGI
System.out.println(SpecT.FERAL.attuneRating); // attunement stat?
// StatT.CRIT
System.out.println(SpecT.FERAL.role); // spec role?
// SpecRoleT.DAMAGE

// can druids use guns?
System.out.println(ClassT.DRUID.canWield(WeaponT.GUN));
// false

// can a level 32 shaman use mail armor?
System.out.println(ClassT.SHAMAN.canWear(32, ArmorT.MAIL));
// false

// what matches...
System.out.println(StatT.names.resolve("aGil")); 
// StatT.AGI
System.out.println(RaceT.names.resolve("bludelferino")); 
// RaceT.BE

// red gem matches what sockets?
System.out.println(GemT.RED.sockets(true));
// [Red, Prismatic]

// red gem fits in what sockets?
System.out.println(GemT.RED.sockets(false));
// [Red, Yellow, Blue, Prismatic] 

// is RegionT.TW an asian region?
System.out.println(RegionT.TW.asia);
// true

// say i have some stats...
StatMap map = new StatMap();
map.add(StatT.AGI_INT, 90);
map.add(StatT.ALL_STATS, 10);
map.add(StatT.AGI, 40);
System.out.println(map.get(StatT.AGI)); // = 40
System.out.println(map.getEffective(StatT.AGI)); // = 140 (90+10+40)

```


###Overview
1. enum-like core game types: StatT, RaceT, ClassT, SpecT, EquipT, ...
2. containers for types: StatMap ...
3. data-like curves: ArmorCurve, ItemLevelCurve, ItemStatCurve, DamageCurve, ...
4. helper tools: SystemHelp, Commented, HttpCache, IconCache, ...



##Enums

##Containers

##Curves

##Helper Tools

###System Help

```java
// open this file!
SystemHelp.openFile("Foo.txt");

// is this a retina display?
SystemHelp.isRetina();

```
SystemHelp contains random garbage for interacting with different operating systems.

###HttpCache
```java
HttpCache hc = new HttpCache(Paths.get("Cache")); // local storage directory
```
HttpCache is an thread-safe, async, caching HTTP client designed to greatly simplify simple web interactions.  Successful queries are stored locally and refreshed when stale.

#####Asynchronous API
```java
public void fetchData(Object key, String url, int maxAge, boolean cache, Callback cb)

@FunctionalInterface
static public interface Callback {
    void gotData(Object key, String error, byte[] data);
}

```
Fetch the `url` provided. If the `key` has already been queried, this request joins that reply.  `maxAge` (millis) determines how old the cache may be before prompting a new request.  Special cases: `maxAge = 0` for any age and `maxAge < 0` to ignore the cache.  The callback is triggered once a result is obtained.

#####Synchronous API
```java
public Result fetchData(String url, int maxAge, boolean cache)

static public class Result {
    public final String error;
    public final byte[] data;
}
```
Fetch the `url` provided and block until the result arrives.  


#####Caching Structure and URL Features
```java
// cache for "http://foo.com/bar.html"
Cache/foo.com/bar.html

// cache for "http://foo.com/a/b/c/d/bar.html"
Cache/foo.com/bar.html

// you can use a |-delimited feature to create a sub directory
// "http://foo.com/a/b/c/d/bar.html|dir=wut
Cache/foo.com/wut/bar.html

// you can also override the file name
// "http://foo.com/a/b/c/d/bar.html|name=wut.html
Cache/foo.com/wut.html

// you can use both of these together
// "http://foo.com/a/b/c/d/bar.html|dir=wut|name=wut.html
Cache/foo.com/wut/wut.html

// you can use # to SHA1 the url
// "http://foo.com/a/b/c/d/bar.html|name=ABC_#.html
Cache/foo.com/A712340AA0BC46BC7F3.html"

```
###IconCache
```java
HttpCache hc = ...
IconCache icon56 = IconCache.zam56(hc); // create a "large" (56x56) wow icon cache
IconCache icon18 = IconCache.zam18_retinaSupport(hc); // create retina-savvy 18x18 (or 36x36) version
```
IconCache is a thread-safe, async icon store designed to work with World of Warcraft icons.

#####Asynchronous API
```java
public ImageIcon get(String key, Callback cb) 

@FunctionalInterface
static public interface Callback {
    void got(String key, String error, ImageIcon icon);
}
```
Get an icon named `key` (eg. "spell_nature_manbearpig") and returns immediately with a blank icon or already cached icon.  If the icon has not been cached, a request is made, and the result is given to the callback.
#####Example
```java
// provide an icon cache
IconCache iconCache = ...
// set the icon now, or set it once it's arrived 
btn.setIcon(iconCache.getIcon("spell_nature_explode", IconCache.swingSafeCallback(btn::setIcon)));
```
#####Miscellaneous Features
```java
// use the default wow '?' icon
IconCache iconCache = ...
iconCache.useOfficialBlank();
```


