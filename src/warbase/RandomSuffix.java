package warbase;

import java.util.function.ToIntFunction;
import warbase.types.SocketT;

public class RandomSuffix {

    public final int id;
    public final String name; 
    public final StatAlloc[] statAllocs; 
    public final SocketT[] sockets;
    
    public RandomSuffix(int id, String name, StatAlloc[] statAllocs, SocketT[] sockets) {
        this.id = id;
        this.name = name;
        this.statAllocs = statAllocs;
        this.sockets = sockets;
    }
    
    @Override
    public String toString() {
        return String.format("%s<%d>(\"%s\")", getClass().getSimpleName(), id, name);
    }
        
    //static final ToIntFunction<RandomSuffix> ID = x -> x.id;
    
}
