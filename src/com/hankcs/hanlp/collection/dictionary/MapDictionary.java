package com.hankcs.hanlp.collection.dictionary;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 词典{@link ITextDictionary}基于{@link Map}的实现
 * 
 * @author TylunasLi
 *
 * @param <V>
 */
public class MapDictionary<V> implements ITextDictionary<V> {
    
    private Map<String, V> concreteDictionary;

    public MapDictionary() {
        concreteDictionary = new HashMap<String, V>();
    }

    public MapDictionary(Map<String, V> wordMap) {
        concreteDictionary = new HashMap<String, V>(wordMap);
    }

    @Override
    public int size() {
        return concreteDictionary.size();
    }

    @Override
    public boolean isEmpty() {
        return concreteDictionary.isEmpty();
    }

    @Override
    public V get(String key) {
        return concreteDictionary.get(key);
    }

    @Override
    public V put(String key, V value) {
        return concreteDictionary.put(key, value);
    }

    @Override
    public boolean containsKey(String key) {
        return concreteDictionary.containsKey(key);
    }

    @Override
    public V remove(String key) {
        return concreteDictionary.remove(key);
    }

    @Override
    public void clear() {
        concreteDictionary.clear();
    }

    @Override
    public Set<String> keySet() {
        return concreteDictionary.keySet();
    }

    @Override
    public Collection<V> values() {
        return concreteDictionary.values();
    }

    @Override
    public Set<Entry<String, V>> entrySet() {
        return concreteDictionary.entrySet();
    }

    @Override
    public Map<String, V> entryMap() {
        return concreteDictionary;
    }

    @Override
    public String toString() {
        return String.format("%s", concreteDictionary);
    }

}
