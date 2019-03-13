package com.hankcs.hanlp.algorithm.lattice;

import java.util.LinkedList;
import java.util.List;

import com.hankcs.hanlp.collection.lattice.CharLattice;
import com.hankcs.hanlp.collection.lattice.CharVertex;
import com.hankcs.hanlp.collection.lattice.Lattice;
import com.hankcs.hanlp.collection.lattice.Vertex;

/**
 * 用 Viterbi规划求最优路径
 * @author TylunasLi
 *
 */
public class ViterbiDecoder implements IPathMatcher {

    @Override
    public List<MatchedVertex> search(Lattice lattice) {
        List<Vertex> vertexList = viterbi(lattice);
        LinkedList<MatchedVertex> result = new LinkedList<MatchedVertex>();
        int start = 0;
        for (Vertex vertex : vertexList) {
            result.add(new MatchedVertex(start, vertex));
            start += vertex.length();
        }
        return result;
    }

    public List<Vertex> viterbi(Lattice wordNet) {
        // 避免生成对象，优化速度
        LinkedList<Vertex> nodes[] = wordNet.getVertexes();
        LinkedList<Vertex> vertexList = new LinkedList<Vertex>();
        for (Vertex node : nodes[1]) {
            updateVertex(nodes[0].getFirst(), node);
        }
        for (int i = 1; i < nodes.length - 1; ++i) {
            LinkedList<Vertex> nodeArray = nodes[i];
            if (nodeArray == null) continue;
            for (Vertex node : nodeArray)
            {
                if (node.from == null) continue;
                for (Vertex to : nodes[i + node.length()]) {
                    updateVertex(node, to);
                }
            }
        }
        Vertex from = nodes[nodes.length - 1].getFirst();
        while (from != null)
        {
            vertexList.addFirst(from);
            from = from.from;
        }
        return vertexList.subList(1, vertexList.size()-1);
    }

    
    public boolean updateVertex(Vertex from, Vertex to) {
        double weight = from.totalWeightFrom + calculateWeight(from, to);
        if (to.from == null || to.totalWeightFrom < weight) {
            to.from = from;
            to.totalWeightFrom = weight;
            return true;
        }
        return false;
    }

    /**
     * 继承此方法计算路径部分的权重
     * @param from
     * @param to
     * @return
     */
    public double calculateWeight(Vertex from, Vertex to) {
        return to.selfWeight;
    }
}
