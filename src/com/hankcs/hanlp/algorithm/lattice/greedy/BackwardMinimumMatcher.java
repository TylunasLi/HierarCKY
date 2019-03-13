package com.hankcs.hanlp.algorithm.lattice.greedy;

import java.util.LinkedList;
import java.util.List;

import com.hankcs.hanlp.algorithm.lattice.IPathMatcher;
import com.hankcs.hanlp.algorithm.lattice.MatchedVertex;
import com.hankcs.hanlp.collection.lattice.Lattice;
import com.hankcs.hanlp.collection.lattice.Vertex;

/**
 *  词网上的逆项最小匹配方法
 * 
 *  @author TylunasLi
 */
public class BackwardMinimumMatcher implements IPathMatcher {

    @Override
    public List<MatchedVertex> search(Lattice lattice) {
        if (!lattice.isReversed())
            lattice.reverse();
        LinkedList<MatchedVertex> result = new LinkedList<MatchedVertex>();
        LinkedList<Vertex>[] graph = lattice.getVertexes();
        for (int offset = graph.length - 2; offset > 0; ) {
            if (graph[offset] == null || graph[offset].isEmpty()) {
                --offset;
                continue;
            }
            Vertex shortest = null;
            int minLength = offset;
            for (Vertex vertex : graph[offset]) {
                if (vertex.length() < minLength) {
                    minLength = vertex.length();
                    shortest = vertex;
                }
            }
            int begin = offset - shortest.length() + 1;
            /** 保存结果. */
            result.addFirst(new MatchedVertex(begin, shortest));
            offset = begin-1;
        }
        return result;
    }

}
