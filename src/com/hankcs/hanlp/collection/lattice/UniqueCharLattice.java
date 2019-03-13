package com.hankcs.hanlp.collection.lattice;

/**
 * 
 * 词语/短语构成的序列划分有向无环图
 * 序列划分有向无环图节点均为序列中的片段 
 * @author TylunasLi
 *
 */
public class UniqueCharLattice extends UniqueLattice {
    
    /**
     * 原始句子对应的数组
     */
    public char[] charArray;

    public UniqueCharLattice(char[] charArray) {
        super(charArray.length);
        this.charArray = charArray;
        vertexes[0].clear();
        vertexes[0].add(CharVertex.createFirstNode());
        vertexes[vertexes.length-1].clear();
        vertexes[vertexes.length-1].add(CharVertex.createLastNode());
    }
    
}
