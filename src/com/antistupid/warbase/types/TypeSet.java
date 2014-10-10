package com.antistupid.warbase.types;

import java.util.Comparator;
import java.util.TreeMap;

public class TypeSet<T extends TypeT> {

    
    
    
    static final Comparator<long[]> CMP_BITS = (a, b) -> {
        int n = a.length;
        int c = Integer.compare(n, b.length);
        if (c != 0) return c;
        for (int i = 0; i < n; i++) {
            c = Long.compare(a[i], b[i]);
            if (c != 0) return c;
        }
        return 0;
    };
    
    static public class Memo<T extends TypeT,S extends TypeSet<T>> {
        
        final TreeMap<long[],S> map = new TreeMap<>(CMP_BITS);
        
        
    }
    
}
