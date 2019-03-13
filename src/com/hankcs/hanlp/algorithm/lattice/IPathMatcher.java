package com.hankcs.hanlp.algorithm.lattice;

import java.util.List;

import com.hankcs.hanlp.collection.lattice.Lattice;

/**
 *  词网上顶点的搜索方法
 *  
 *  @author TylunasLi
 */
public interface IPathMatcher {

    /**
     * 搜索方法
     * @param  lattice  词网
     * @return 最优路径上的顶点
     */
    List<MatchedVertex> search(Lattice lattice);

}
