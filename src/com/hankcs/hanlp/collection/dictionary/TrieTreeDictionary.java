package com.hankcs.hanlp.collection.dictionary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;

import com.hankcs.hanlp.collection.trie.bintrie.BinTrie;
import com.hankcs.hanlp.collection.trie.bintrie.BaseNode.IBinTrieWalker;

/**
 * 词典{@link ITextDictionary}基于二叉Trie树{@link BinTrie}的实现
 * 
 * @author TylunasLi
 *
 * @param <V>
 */
public class TrieTreeDictionary<V> implements ITextDictionary<V> {
    
    private BinTrie<V> concreteDictionary;

    public TrieTreeDictionary() {
        concreteDictionary = new BinTrie<V>();
    } 
    
    @Override
    public int size() {
        return concreteDictionary.size();
    }

    @Override
    public boolean isEmpty() {
        return (concreteDictionary.size() == 0);
    }

    @Override
    public boolean containsKey(String key) {
        return concreteDictionary.containsKey(key);
    }

    @Override
    public V get(String key) {
        return concreteDictionary.get(key);
    }

    @Override
    public V put(String key, V value) {
        V oldValue = concreteDictionary.get(key);
        concreteDictionary.put(key, value);
        return oldValue;
    }

    @Override
    public V remove(String key) {
        V oldValue = concreteDictionary.get(key);
        concreteDictionary.remove(key);
        return oldValue;
    }

    @Override
    public void clear() {
        concreteDictionary = null;
        concreteDictionary = new BinTrie<V>();
    }

    @Override
    public Set<String> keySet() {
        return concreteDictionary.keySet();
    }

    @Override
    public Collection<V> values() {
        final ArrayList<V> values = new ArrayList<V>();
        concreteDictionary.walk(new IBinTrieWalker<V>() {
            @Override
            public void onNodeWalked(String key, V value) {
                values.add(value);
            }
        });
        return values;
    }

    @Override
    public Set<Entry<String, V>> entrySet() {
        return concreteDictionary.entrySet();
    }

    @Override
    public Map<String, V> entryMap() {
        final Map<String, V> resultMap = new HashMap<String, V>();
        concreteDictionary.walk(new IBinTrieWalker<V>() {
            @Override
            public void onNodeWalked(String key, V value) {
                resultMap.put(key, value);
            }
        });
        return resultMap;
    }

}
