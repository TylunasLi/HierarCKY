package com.hankcs.hanlp.algorithm.lattice.greedy;

import java.util.LinkedList;
import java.util.List;

import com.hankcs.hanlp.algorithm.lattice.IPathMatcher;
import com.hankcs.hanlp.algorithm.lattice.MatchedVertex;
import com.hankcs.hanlp.collection.lattice.Lattice;
import com.hankcs.hanlp.collection.lattice.Vertex;

/**
 *  词网上的正项最大匹配算法
 * 
 *  @author TylunasLi
 */
public class ForwardMaximumMatcher implements IPathMatcher {

    @Override
    public List<MatchedVertex> search(Lattice lattice) {
        LinkedList<MatchedVertex> result = new LinkedList<MatchedVertex>();
        LinkedList<Vertex>[] graph = lattice.getVertexes();
        for (int offset = 1; offset < graph.length-1; ) {
            if (graph[offset] == null || graph[offset].isEmpty()) {
                ++offset;
                continue;
            }
            Vertex longest = null;
            int maxLength = 0;
            for (Vertex vertex : graph[offset]) {
                if (vertex.length() > maxLength) {
                    maxLength = vertex.length();
                    longest = vertex;
                }
            }
            /** 保存结果. */
            result.add(new MatchedVertex(offset, longest));
            offset = offset + longest.length();
        }
        return result;
    }

}
