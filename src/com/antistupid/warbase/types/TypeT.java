package com.antistupid.warbase.types;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

abstract public class TypeT {
    
    public final int index;
    public final int id;
    public final String name;
    
    TypeT(int index, int id, String name) {
        this.index = index;
        this.id = id;
        this.name = name;
    }
    
    void registerNames(Consumer<String> c) {
        c.accept(name);
    }
        
    public String getNameAndId() {
        return String.format("%s<%d>", name, id);
    }
        
    @Override
    public String toString() {
        return name;
    }
           
    static public boolean checkBit(long mask, TypeT type) { // null safe
        return type != null && type.isMemberOf(mask); 
    }
        
    public boolean isMemberOf_passZero(long mask) {
        return mask == 0 || isMemberOf(mask);
    }
    
    public boolean isMemberOf(long mask) {
        return (getBit() & mask) != 0L;
    }
    
    public long getBit() {
        return 1L << index;
    }
    
    //
    
    static public class Container<T extends TypeT> {
        public final Class<T> cls; // was there a reason why i wasn't retaining this?
        public final T[] types;         
        public final Assoc<T> by_id;
        Container(Class<T> cls) {      
            this.cls = cls;
            types = indexed(cls);
            by_id = assoc(cls);
        }     
        public T[] selectArray(Predicate<T> test) {
            ArrayList<T> list = selectList(test);
            return list.toArray((T[])Array.newInstance(cls, list.size()));
        }
        public ArrayList<T> selectList(Predicate<T> test) {
            ArrayList<T> list = new ArrayList<>(types.length);
            for (T x: types) {
                if (test.test(x)) {
                    list.add(x);
                }
            }
            return list;
        }            
        public void forEach(Consumer<T> cb) {
            for (T x: types) {
                cb.accept(x);
            }
        }
        public int[] createFastLookup(T... a) {
            int[] v = new int[types.length];
            Arrays.fill(v, -1);
            for (int i = 0; i < a.length; i++) {
                v[a[i].index] = i;
            }
            return v;
        }
        public int size() {
            return types.length;
        }
        @Override
        public String toString() {
            return Arrays.toString(types);
        }
    }
    
    // ---
    
    static public class BitContainer<T extends TypeT> extends Container<T> {
        public final long allMask;
        BitContainer(Class<T> cls) {
            super(cls);
            if (types.length > 64) {
                throw new IllegalArgumentException("Too many types: " + cls);
            } else if (types.length == 64) {
                allMask = -1L;
            } else {
                allMask = (1L << types.length) - 1L;
            }                                                
        }
        public void appendTo(StringBuilder sb, long bits) {
            boolean first = true;
            for (int i = 0; i < types.length && bits != 0; i++, bits >>= 1) {
                if ((bits & 1L) == 1L) {
                    if (first) {
                        first = false;
                    } else {
                        sb.append(", ");
                    }
                    sb.append(types[i].name);
                }              
            }
        }
        public ArrayList<T> toList(long bits) {
            ArrayList<T> list = new ArrayList<>(types.length);
            forEach(bits, list::add);
            return list;
        }        
        public void forEach(long bits, Consumer<T> c) {
            for (int i = 0; i < types.length && bits != 0; i++, bits >>= 1) {
                if ((bits & 1L) == 1L) {
                    c.accept(types[i]);
                }              
            }
        }
        public long encode(T... a) { return encode(0, a); }
        public long encode(long bits, T... a) {
            for (T x: a) {
                bits |= x.getBit();
            }            
            return bits;
        }
        
        public T first(long bits) { return nth(bits, 0); }
        public T nth(long bits, int index) {
            for (int i = 0; i < types.length && bits != 0; i++, bits >>= 1) {
                if ((bits & 1L) == 1L && index-- == 0) {
                    return types[i];
                }        
            }
            return null;
        }
        
    }
    
    // ---
    
    static public class BlizzBit<T extends TypeT> {        
        final ToIntFunction<T> f;
        final Assoc<T> assoc;
        final int allMask;
        BlizzBit(Class<T> cls) { this(cls, x -> 1 << x.id); }            
        BlizzBit(Class<T> cls, ToIntFunction<T> f) {
            assoc = assoc(cls, f, getClass().getSimpleName());
            this.f = f;
            int mask = 0;
            for (T x: assoc.types) {
                mask |= f.applyAsInt(x);
            }
            allMask = mask;
        }
        public int get(T type) {
            return f.applyAsInt(type);
        }
        public long decode(int blizzMask) {
            long mask = 0L;
            while (blizzMask != 0) {
                int x = Integer.highestOneBit(blizzMask);                
                T type = assoc.get(x);
                if (type != null) {
                    mask |= type.getBit();
                }    
                blizzMask &= ~x;
            }
            return mask;            
        }
        public int encode(T... types) {
            int blizzMask = 0;
            for (T x: types) {
                blizzMask |= f.applyAsInt(x);            
            }   
            return blizzMask;
        }
        public int encode(long mask) {
            int blizzMask = 0;
            for (T x: assoc.types) {
                if (x.isMemberOf(mask)) {
                    blizzMask |= f.applyAsInt(x);
                }                
            }            
            return blizzMask;
        }
    }
    
    
    // ---
    
    static <T extends TypeT> T[] indexed(Class<T> cls) {        
        ArrayList<T> found = new ArrayList<>();
        for (Field f: cls.getDeclaredFields()) {
            if (cls.isAssignableFrom(f.getType())) {
                try {
                    T type = (T)f.get(null);
                    found.add(type);                    
                } catch (IllegalAccessException err) {
                    //err.printStackTrace();
                }                
            }
        }
        int size = found.size();
        if (size == 0) {
            throw new RuntimeException(cls + " is empty");
        }  
        T[] types = (T[])Array.newInstance(cls, size);        
        for (T x: found) {
            if (x.index < 0 || x.index >= size) {
                throw new IllegalArgumentException(cls + " Bad Index: " + x.index);
            }
            if (types[x.index] != null) {
                throw new IllegalArgumentException(cls + " Index Collision: " + x.index);
            }
            types[x.index] = x;
        }
        return types;
    }
    
    static public <T extends TypeT> Assoc<T> assoc(Class<T> cls) { return assoc(cls, x -> x.id, "Id"); }    
    static public <T extends TypeT> Assoc<T> assoc(Class<T> cls, ToIntFunction<T> key, String suffix) {        
        T[] sorted = indexed(cls);        
        int n = sorted.length;
        Arrays.sort(sorted, (a, b) -> Integer.compare(key.applyAsInt(a), key.applyAsInt(b)));
        int[] keys = new int[n];
        for (int i = 0; i < n; i++) {
            keys[i] = key.applyAsInt(sorted[i]);
        }
        String desc = cls.getSimpleName() + " " + suffix;
        for (int i = 1; i < n; i++) {
            if (keys[i - 1] == keys[i]) {
                throw new IllegalArgumentException(cls + " Collision: " + keys[i]);
            }
        }
        int keyOffset = keys[0];
        int span = 1 + keys[n - 1] - keyOffset;
        if (span == n) {
            return new Dense<>(desc, sorted, keyOffset);
        } else if (span <= 2 * n && span > 0) { 
            int[] where = new int[span];
            Arrays.fill(where, -1);
            for (int i = 0; i < n; i++) {
                where[keys[i] - keyOffset] = i;
            }            
            return new Sparse<>(desc, sorted, keyOffset, where);
        } else {            
            return new Search<>(desc, sorted, keys);
        }        
    }
    
    static abstract public class Assoc<T extends TypeT> {
        final String desc;
        final T[] types;
        Assoc(String desc, T[] sorted) {
            this.desc = desc;
            this.types = sorted;
        }
        abstract public int indexOf(int key);
        public T get(int key) {
            int i = indexOf(key);
            return i >= 0 ? types[i] : null;
        }
        public T require(int key) {
            T type = get(key);
            if (type == null) {
                throw new IllegalArgumentException(String.format("Unknown %s: %d", desc, key));
            }
            return type;
        }
        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" + desc + ")" + Arrays.toString(types);
        }
    }
    
    static private class Dense<T extends TypeT> extends Assoc<T> {
        final int keyOffset;
        Dense(String desc, T[] sorted, int keyOffset) {
            super(desc, sorted);
            this.keyOffset = keyOffset;
        }
        @Override
        public int indexOf(int key) {
            int i = key - keyOffset;
            return i >= 0 && i < types.length ? i : -1;
        }
    }
    
    static private class Sparse<T extends TypeT> extends Assoc<T> {
        final int keyOffset;
        final int[] indexes;
        Sparse(String desc, T[] sorted, int keyOffset, int[] indexes) {
            super(desc, sorted);
            this.keyOffset = keyOffset;
            this.indexes = indexes;
        }
        @Override
        public int indexOf(int key) {
            int i = key - keyOffset;
            return i >= 0 && i < indexes.length ? indexes[i] : -1;
        }
    }
    
    static private class Search<T extends TypeT> extends Assoc<T> {
        final int[] keys;
        Search(String desc, T[] sorted, int[] keys) {            
            super(desc, sorted);
            this.keys = keys;
        }
        @Override
        public int indexOf(int key) {
            return Arrays.binarySearch(keys, key);
        } 
    }
    

}
