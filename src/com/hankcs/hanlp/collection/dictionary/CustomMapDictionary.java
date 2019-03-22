package com.hankcs.hanlp.collection.dictionary;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 自己定义了一次散列函数的哈希表词典
 * 
 * @author TylunasLi
 *
 * @param <V>
 */
public class CustomMapDictionary<V> implements ITextDictionary<V> {
    
    private HashMap<Integer, Map.Entry<String,V>[]> concreteDict;
    private int size;

    private static int defaultCompactLevel = 5;
    private int compactMask = (0xffffffff >> (3 + defaultCompactLevel)) << 2;

    public CustomMapDictionary() {
        concreteDict = new HashMap<Integer, Map.Entry<String,V>[]>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return concreteDict.isEmpty();
    }


    @SuppressWarnings("unchecked")
    protected int getStringHash(String word) {
        int len = word.length();
        int lenMask = len;
        if (lenMask > 3) lenMask = 3;

        long hash = 2166136261L;
        for (int i = 0; i < len; i++) {
                hash ^= word.charAt(i);
                hash *= 16777619;
        }

        hash &= this.compactMask;
        hash |= (long)lenMask;
        return (int)hash;
    }

    @Override
    public V get(String key) {
        if (key == null)
            return null;
        int hashedKey = getStringHash(key);
        Map.Entry<String,V>[] bucket = concreteDict.get(hashedKey);
        if (bucket == null)
            return null;
        for (Map.Entry<String,V> entry : bucket) {
            if ( entry != null && key.equals(entry.getKey()) )
                return entry.getValue();
        }
        return null;
    }

    @Override
    public V put(String key, V value) {
        V oldValue = null;
        if (key == null)
            return null;
        int hashedKey = getStringHash(key);
        Map.Entry<String,V>[] bucket = concreteDict.get(hashedKey);
        if (bucket == null) {
            oldValue = null;
            bucket = new Map.Entry[1];
            bucket[0] = new AbstractMap.SimpleEntry<String,V>(key, value);
            concreteDict.put(hashedKey, bucket);
            size++;
        } else {
            for (Map.Entry<String,V> entry : bucket) {
                if ( entry != null && entry.getKey().equals(key) ) {
                    oldValue = entry.getValue();
                    entry.setValue(value);
                }
            }
            if (oldValue == null) {
                bucket = expandOne(bucket);
                bucket[bucket.length-1] = new AbstractMap.SimpleEntry<String,V>(key, value);
                size++;
            }
        }
        return oldValue;
    }

    private Entry<String, V>[] expandOne(Entry<String, V>[] oldArray) {
        Entry<String, V>[] newArray = new Entry[oldArray.length+1];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        return newArray;
    }

    @Override
    public boolean containsKey(String key) {
        if (key == null)
            return false;
        int hashedKey = getStringHash(key);
        Map.Entry<String,V>[] bucket = concreteDict.get(hashedKey);
        if (bucket == null)
            return false;
        for (Map.Entry<String,V> entry : bucket) {
            if ( entry != null && key.equals(entry.getKey()) )
                return true;
        }
        return false;
    }

    @Override
    public V remove(String key) {
        V oldValue = get(key);
        if (oldValue == null)
            return null;
        Map.Entry<String,V>[] bucket = concreteDict.get(getStringHash(key));
        for (int i=0; i<bucket.length; i++) {
            if ( bucket[i] != null && key.equals(bucket[i].getKey()) )
                bucket[i] = null;
        }
        return oldValue;
    }

    public void clear() {
        concreteDict.clear();
    }

    public Set<String> keySet() {
        Collection<Map.Entry<String,V>[]> values = concreteDict.values();
        HashSet<String> results = new HashSet<String>(size*3/4);
        for (Map.Entry<String,V>[] bucket : values) {
            for (Map.Entry<String,V> entry : bucket) {
                if (entry != null)
                    results.add(entry.getKey());
            }
        }
        return results;
    }

    public Collection<V> values() {
        Collection<Map.Entry<String,V>[]> values = concreteDict.values();
        ArrayList<V> results = new ArrayList<V>(size);
        for (Map.Entry<String,V>[] bucket : values) {
            for (Map.Entry<String,V> entry : bucket) {
                if (entry != null)
                    results.add(entry.getValue());
            }
        }
        return results;
    }

    public Set<Entry<String, V>> entrySet() {
        Collection<Map.Entry<String,V>[]> values = concreteDict.values();
        HashSet<Entry<String, V>> results = new HashSet<Entry<String, V>>(size*3/4);
        for (Map.Entry<String,V>[] bucket : values) {
            for (Map.Entry<String,V> entry : bucket) {
                if (entry != null)
                    results.add(entry);
            }
        }
        return results;
    }

    @Override
    public Map<String, V> entryMap() {
        Collection<Map.Entry<String,V>[]> values = concreteDict.values();
        HashMap<String, V> results = new HashMap<String, V>(size*3/4);
        for (Map.Entry<String,V>[] bucket : values) {
            for (Map.Entry<String,V> entry : bucket) {
                if (entry != null)
                    results.put(entry.getKey(),entry.getValue());
            }
        }
        return results;
    }
}
