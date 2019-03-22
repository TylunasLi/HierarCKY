package com.hankcs.hanlp.collection.dictionary;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * 所有词典容器的包装接口
 * 
 * @author TylunasLi
 *
 * @param <V> 词典中值的类型
 */
public interface ITextDictionary<V> {

    /**
     * 返回词典中的词条数量
     * 
     * @return 词典中的词条（key-value）数量
     */
    public int size();

    /**
     * 如果词典不包含词条，返回 <tt>true</tt>。
     *
     * @return 如果词典不包含词条则为<tt>true</tt>。
     */
    public boolean isEmpty();
    
    /**
     * 若词典含有特定的<tt>key</tt>，则返回  <tt>true</tt>。更一般地说。  当且仅当词典中
     * 含有一个满足<tt>(key==null ? k==null : key.equals(k))</tt>的词条
     * <tt>k</tt>时，  接口才返回 <tt>true</tt> 。这样的映射最多只能有一个。
     *
     * @param key 需要检验的词条
     * @return 含有这个词条时返回 <tt>true</tt>。 
     */
    public boolean containsKey(String key);

    /**
     * 返回指定词条在词典中对应的值。如果词典不包含这个词条，则返回 {@code null}。
     * 
     * @param key 需要检索的的词条
     * @return 词典中对应的值
     */
    public V get(String key);

    /**
     * 在词典中将指定的词条<tt>key</tt>和指定的值<tt>value</tt>关联起来。如果词典中已经包含这个词条，
     * 就会用新的值替换旧的值，并返回旧的值。
     *
     * @param key 需要和特定值关联起来的词条
     * @param value 需要和特定词条关联起来的值
     * @return 之前与词条<tt>key</tt>关联的值，
     *         如果当前的词条 <tt>key</tt>以前并不存在则返回<tt>null</tt>。
     */
    public V put(String key, V value);

    /**
     * 如果指定词条在词典中存在，则将其移除， {@code null}。
     * 
     * @param key 需要删除的的词条
     * @return 之前与词条<tt>key</tt>关联的值，
     *         如果当前的词条 <tt>key</tt>以前并不存在则返回<tt>null</tt>。
     */
    public V remove(String key);

    /**
     * 移除词典中的所有词条。调用该方法后词典内部将变为空。
     */
    public void clear();

    /**
     * 以 {@link Set} 形式返回这个词典中所有词条的集合 
     */
    public Set<String> keySet();

    /**
     * 以 {@link Collection} 形式返回这个词典中所有值的集合
     */
    public Collection<V> values();
    
    /**
     * 以{@link Set}形式返回这个词典中所有词条-值的集合。
     */
    public Set<Map.Entry<String, V>> entrySet();

    
    /**
     * 以{@link Map}形式返回这个词典中所有词条-值的集合。
     */
    public Map<String, V> entryMap();
    
}
