package com.antistupid.warbase;

import java.util.Arrays;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

public class IntSet {
    
    static private int makeSorted(int[] src, int srcNum, int[] dst) {
        System.arraycopy(src, 0, dst, 0, srcNum);
        Arrays.sort(dst, 0, srcNum);
        int shift = 0;
        int last = dst[0];
        for (int i = 1; i < srcNum; i++) {
            int k = dst[i];
            if (k == last) {
                shift++;
            } else {
                if (shift > 0) {
                    dst[i - shift] = k;
                }
                last = k;
            }
        }
        return srcNum - shift; // dstNum
    }
    
    static public IntSet fromUnsorted(int... a) {
        return fromUnsorted(a, a.length, 0);
    }
    
    static public IntSet fromUnsorted(int[] array, int num, int room) {
        IntSet temp = new IntSet(num + room); 
        temp.num = makeSorted(array, num, temp.keys);
        return temp;
    }
    
    public int[] keys;
    private int num;
    
    public IntSet() { this(0); }    
    public IntSet(int capacity) {
        keys = new int[Math.max(8, capacity)];
    }
    
    public int[] toArray() {
        return Arrays.copyOf(keys, num);
    }
    
    public int size() {
        return num;
    }
    
    public IntSet copy(int room) {
        IntSet copy = new IntSet(num + Math.max(0, room));
        System.arraycopy(keys, 0, copy.keys, 0, num);
        copy.num = num;
        return copy;
    }
    
    public void clear() {
        num = 0;
    }
    
    public boolean isEmpty() { return num == 0; }
    
    private int at(int key) {
        return Arrays.binarySearch(keys, 0, num, key);
    }
    
    public void set(IntSet set) {
        clear();
        add(set);
    }
    
    public int add(IntSet set) {
        int added = 0;
        for (int i = 0; i < set.num; i++) {
            if (add(set.keys[i])) {
                added++;
            }
        }                          
        return added;
    }
    
    public boolean add(int key) {
        int i = at(key);
        if (i >= 0) {
            return false;
        }      
        i = ~i;        
        if (num == keys.length) { 
            int[] copy = new int[num << 1]; // fixme: choose this constant factor so resizes never really happen
            System.arraycopy(keys, 0, copy, 0, i);
            copy[i] = key;
            System.arraycopy(keys, i, copy, i + 1, num - i);  
            keys = copy;
        } else {        
            System.arraycopy(keys, i, keys, i + 1, num - i);                
            keys[i] = key;
        }
        ++num;
        return true;       
    }
    
    public boolean contains(int key) {
        return at(key) >= 0;
    }
    
    public boolean remove(int key) {
        int i = at(key);
        if (i < 0) {
            return false;
        }
        if (i < --num) {
            System.arraycopy(keys, i + 1, keys, i, num - i);
        }
        return true;
    }
    
    public int removeIf(IntPredicate test) {
        int removed = 0;
        for (int i = 0; i < num; i++) {
            int key = keys[i];
            if (test.test(key)) {
                removed++;
            } else if (removed > 0) {
                keys[i - removed] = key;
            }
        }        
        num -= removed;
        return removed;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        appendTo(sb, ", ");
        sb.append("]");
        return sb.toString();
    }
    
    public void appendTo(StringBuilder sb, String sep) {
        if (num == 0) {
            return;
        }
        sb.append(keys[0]);        
        for (int i = 1; i < num; i++) {
            sb.append(sep);
            sb.append(keys[i]);
        }        
    }
    
    
    public void forEach(IntConsumer f) {
        for (int i = 0; i < num; i++) {
            f.accept(keys[i]);
        }
    }
    
    static private int removeAll(IntSet set, int[] keys, int num) {
        int shift = 0;
        for (int i = 0; i < num; i++) {
            int k = keys[i];
            if (set.contains(k)) {
                shift++;
            } else if (shift > 0) {
                keys[i - shift] = k;
            }            
        }
        return num - shift;
    }
    
    public int removeAll(IntSet set) {
        int prev = num;
        num = removeAll(set, keys, prev);
        return prev - num;
    }
    
    
}
