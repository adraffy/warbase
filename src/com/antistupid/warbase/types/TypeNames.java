package com.antistupid.warbase.types;

import java.util.HashSet;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Predicate;

public class TypeNames<T extends TypeT> {
  
    final TreeMap<String,T> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    
    public TypeNames(T[] types) { this(types, false); }
    public TypeNames(T[] types, boolean removeCollisions) {
        HashSet<String> dead = removeCollisions ? new HashSet() : null;
        for(T x: types) {
            register(x, x.name, dead);
            x.registerNames(name -> register(x, name, dead));
        }              
    }
    
    private void register(T type, String name, HashSet<String> dead) {               
        String key = name.toLowerCase();
        if (dead != null && dead.contains(key)) {
            return;
        }
        T old = map.put(key, type);
        if (old != null && old != type) {
            if (dead != null) {
                dead.add(key);
                map.remove(key);
            } else {
                throw new RuntimeException(String.format("Collision \"%s\" %s <> %s", key, old.getNameAndId(), type.getNameAndId()));
            }
        }   
    }
    
    
    public T resolve(String name) { return resolve(name, x -> true); }
    public T resolve(String name, Predicate<T> filter) {
        name = name.trim();
        T temp = map.get(name);
        if (temp != null) {
            return temp;
        }
        name = name.toLowerCase();
        while (name.length() > 0) {
            SortedMap<String,T> sub = map.subMap(name, name + "z"); // kappa, fix this code later
            if (sub.isEmpty()) {
                break;
            }
            Iterator<T> iter = sub.values().iterator();
            T first = null;
            fail: {
                while (iter.hasNext()) {
                    T next = iter.next();
                    if (!filter.test(next)) {
                        continue;
                    } 
                    if (first == null) {
                        first = next;
                    } else if (first != next) {
                        break fail;
                    }
                }
                return first;
            }
            if (first == null) {
                break; // nothing matched filter
            }
            name = name.substring(0, name.length() - 1); // drop a character            
        }        
        return null;
    }
    
    
    public T get(String name) {
        return map.get(name);  
    }
    
    public T require(String name) {
        T temp = map.get(name);
        if (temp == null) {
            throw new IllegalArgumentException(String.format("Unknown %s type: \"%s\"", null, name));
        }
        return temp;
    }
    
}
