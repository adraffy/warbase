package com.antistupid.warbase.utils;

import java.util.Comparator;

public class Misc {
    
    static public int compareForNullAtBottom(Object a, Object b) {
        if (a == null) {
            return b == null ? 0 : -1;
        } else if (b == null) {
            return 1;
        } else {
            return 0;
        }
    }
    
    static public <T> Comparator<T[]> arrayComparator(Comparator<T> cmp) {        
        return (a, b) -> {
            if (a == null) {
                return b == null ? 0 : 1;
            } else if (b == null) {
                return -1;
            }
            int n = a.length;
            if (n != b.length) {
                return Integer.compare(n, b.length);
            }      
            for (int i = 0; i < n; i++) {
                int c = cmp.compare(a[i], b[i]);
                if (c != 0) {
                    return c;
                }
            }        
            return 0;
        };
    }
    
    static public int compare(int[] a, int[] b) {
        int n = a.length;
        int c = n - b.length;
        if (c != 0) {
            return c;
        }
        for (int i = 0; i < n; i++) {
            c = Integer.compare(a[i], b[i]);
            if (c != 0) {
                return c;
            }
        }
        return 0;
    }
  
}
