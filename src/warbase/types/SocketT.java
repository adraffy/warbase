package warbase.types;

import java.util.ArrayList;

public class SocketT extends TypeT {
    
    private final int acceptMask;
    
    public SocketT(int index, int id, String name) { this(index, id, name, id); }
    public SocketT(int index, int id, String name, int acceptMask) {
        super(index, id, name);
        this.acceptMask = acceptMask == 0 ? id : acceptMask;
    }
    
    public boolean isColored() {
        return acceptMask == GemBits.PRISMATIC;
    }
    
    public boolean matches(GemT gem, boolean bonus) {
        return gem != null && matches(gem.mask, bonus);
    }
    public boolean matches(int colorMask, boolean bonus) {
        return ((bonus ? id : acceptMask) & colorMask) != 0;
    }
    
    public ArrayList<GemT> gems(boolean bonus) {
        return GemT.db.select(x -> matches(x, bonus));
    }
        
    static public final SocketT META        = new SocketT(0, GemBits.META,       "Meta");
    static public final SocketT RED         = new SocketT(1, GemBits.RED,        "Red",      GemBits.PRISMATIC);
    static public final SocketT BLUE        = new SocketT(2, GemBits.BLUE,       "Blue",     GemBits.PRISMATIC);
    static public final SocketT YELLOW      = new SocketT(3, GemBits.YELLOW,     "Yellow",   GemBits.PRISMATIC);
    static public final SocketT PRISMATIC   = new SocketT(4, GemBits.PRISMATIC,  "Prismatic",GemBits.PRISMATIC);
    static public final SocketT SHA         = new SocketT(5, GemBits.SHA,        "Sha");
    static public final SocketT COG         = new SocketT(6, GemBits.COG,        "Cog");

    static public final Container<SocketT> db = new Container<>(SocketT.class);    
    
}
