package com.hankcs.hanlp.collection.lattice;

import java.util.LinkedList;

/**
 * 
 * 词图 (Lattice)
 * 词图是一个有向无环图，入度为零和出度为零的顶点有且仅各有一个
 * 在本类中长度相等的顶点是唯一的
 * @author TylunasLi
 *
 */
public class UniqueLattice {
    

    /**
     * 节点，每一行都是前缀词，
     */
    protected LinkedList<Vertex> vertexes[];

    /**
     * 共有多少个节点
     */
    protected int length;

    /**
     * 共有多少个节点
     */
    private int size;

    public UniqueLattice(int length) {
        super();
        this.length = length;
        this.vertexes = new LinkedList[length + 2];
        for (int i = 0; i < vertexes.length; ++i)
        {
            vertexes[i] = new LinkedList<Vertex>();
        }
        vertexes[0].add((Vertex) Vertex.createFirstNode());
        vertexes[vertexes.length-1].add((Vertex) Vertex.createLastNode());
    }

    public LinkedList<Vertex>[] getVertexes() {
        return vertexes;
    }

    public int getLength() {
        return length;
    }

    public int getSize() {
        return size;
    }

    /**
     * 获取某一行长度为length的节点
     *
     * @param line
     * @param length
     * @return
     */
    public Vertex get(int line, int length) {
        for (Vertex vertex : vertexes[line]) {
            if (vertex.length() == length) {
                return vertex;
            }
        }
        return null;
    }
    
    /**
     * 添加顶点
     *
     * @param line   行号
     * @param vertex 顶点
     */
    public boolean add(int line, Vertex vertex) {
        if (line >= vertexes.length)
            return false;
        if (line+vertex.length() > vertexes.length)
            return false;
        for (Vertex oldVertex : vertexes[line]) {
            // 保证唯一性
            if (oldVertex.length() == vertex.length()) return false;
        }
        int idx = 0;
        for (Vertex vtx : vertexes[line]) {
            if (vtx.length() > vertex.length()) break;
            idx++;
        }
        vertexes[line].add(idx, vertex);
        ++size;
        return true;
    }

    public Vertex findFirst(int startPos) {
        Vertex shortest = null;
        for (Vertex vertex : vertexes[startPos]) {
            if (shortest == null || shortest.length() > vertex.length()) {
                shortest = vertex;
            }
        }
        return shortest;
    }

    public Vertex findLast(int startPos, int endPos) {
        Vertex shortest = null;
        for (int i=startPos; i<endPos; i++) {
            for (Vertex vertex : vertexes[i]) {
                if (i + vertex.length() == endPos) {
                    if (shortest == null || shortest.length() > vertex.length()) {
                        shortest = vertex;
                    }
                }
            }
        }
        return shortest;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        int line = 0;
        for (LinkedList<Vertex> vertexList : vertexes) {
            sb.append(String.valueOf(line++) + ':' + vertexList.toString()).append("\n");
        }
        return sb.toString();
    }
}
