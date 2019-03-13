package com.hankcs.hanlp.algorithm.lattice;

import com.hankcs.hanlp.collection.lattice.Vertex;

/**
 *  匹配上的顶点
 * 
 *  @author TylunasLi
 *
 */
public class MatchedVertex {

    public MatchedVertex(int start, Vertex vertex) {
        this.start = start;
        this.vertex = vertex;
    }

    /** 匹配的顶点. */
    public Vertex vertex;
    
    /** 开始位置. */
    public int start;

    @Override
    public String toString() {
        return String.format("[vertex:%s, start:%s]", vertex, start);
    }
    
}
