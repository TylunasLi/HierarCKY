package com.hankcs.hanlp.collection.lattice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 词图 (Lattice)
 * 词图是一个有向无环图，入度为零和出度为零的顶点有且仅各有一个
 * 长度相等的顶点不唯一，作为一个Tag相同的顶点
 * 
 * @author TylunasLi
 *
 */
public class Lattice {

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
    protected int size = 0;

    /**
     * 逆向存储
     */
    protected boolean reversed = false;


    protected static final Comparator<Vertex> lengthComprator = new Comparator<Vertex>() {
        @Override
        public int compare(Vertex vertex1, Vertex vertex2) {
            return Integer.compare(vertex2.length(), vertex1.length());
        }
    };
    

    public Lattice(int length) {
        this.length = length;
        this.vertexes = new LinkedList[length + 2];
        for (int i = 0; i < vertexes.length; ++i) {
            vertexes[i] = new LinkedList<Vertex>();
        }
        vertexes[0].add((Vertex) Vertex.createFirstNode());
        vertexes[vertexes.length-1].add((Vertex) Vertex.createLastNode());
    }

    public LinkedList<Vertex>[] getVertexes() {
        return vertexes;
    }
    
    public int length() {
        return length;
    }

    public int getSize() {
        return size;
    }

    public boolean isReversed() {
        return reversed;
    }

    public void reverse() {
        if (reversed)
            return;
        this.reversed = true;
        // 翻转
        LinkedList<Vertex>[] newVertexes = new LinkedList[length + 2];
        for (int i = 1; i < newVertexes.length-1; ++i) {
            newVertexes[i] = new LinkedList<Vertex>();
        }
        newVertexes[0] = this.vertexes[0];
        newVertexes[newVertexes.length-1] = this.vertexes[newVertexes.length-1];
        for (int i = 1; i < newVertexes.length-1; ++i) {
            for (Vertex vertex : this.vertexes[i]) {
                newVertexes[i+vertex.length()-1].add(vertex);
            }
        }
        this.vertexes = newVertexes;
    }

    /**
     * 获取某一行长度为length的节点
     *
     * @param line
     * @param length
     * @return
     */
    public List<Vertex> getNodes(int line, int length) {
        if (reversed)
            getSepcificNode(line+length-1, length);
        return getSepcificNode(line, length);
    }

    private List<Vertex> getSepcificNode(int line, int length) {
        List<Vertex> list = new ArrayList<Vertex>();
        for (Vertex vertex : vertexes[line]) {
            if (vertex.length() == length) {
                list.add(vertex);
            }
        }
        return list;
    }

    public List<Vertex> getNodesAt(int start) {
        if (start <= 0)
            return Collections.emptyList();
        if (start >= vertexes.length)
            return Collections.emptyList();
        return vertexes[start];
    }

    /**
     * 获取某一行长度为length, type为type的节点
     *
     * @param line
     * @param length
     * @param type
     * @return
     */
    public Vertex get(int line, int length, String type) {
        if (reversed)
            getPrivate(line+length-1, length, type);
        return getPrivate(line, length, type);
    }

    private Vertex getPrivate(int line, int length, String type) {
        for (Vertex vertex : vertexes[line]) {
            if (vertex.length() == length && type.equals(vertex.getTag())) {
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
        if (reversed)
            line = line + vertex.length() - 1;
        Vertex existed = getPrivate(line, vertex.length(), vertex.getTag());
        if (existed != null)
            return false;
        int idx = 0;
        for (Vertex vtx : vertexes[line]) {
            if (vtx.length() > vertex.length()) break;
            idx++;
        }
        vertexes[line].add(idx, vertex);
        size++;
        return true;
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

    public Vertex findFirst(int startPos) {
        if (reversed)
            return findShortestInRange(startPos, vertexes.length-1);
        return getShortest(startPos);
    }

    public Vertex findLast(int startPos, int endPos) {
        if (reversed)
            return getShortest(endPos);
        return findShortestInRange(startPos, endPos);
    }

    private Vertex getShortest(int startPos) {
        Vertex shortest = null;
        for (Vertex vertex : vertexes[startPos]) {
            if (shortest == null || shortest.length() > vertex.length()) {
                shortest = vertex;
            }
        }
        return shortest;
    }

    private Vertex findShortestInRange(int startPos, int endPos) {
        Vertex shortest = null;
        for (int i=startPos; i<endPos; i++) {
            for (Vertex vertex : vertexes[i]) {
                boolean fit = reversed ? i - vertex.length() == endPos : i + vertex.length() == endPos;
                if (fit) {
                    if (shortest == null || shortest.length() > vertex.length()) {
                        shortest = vertex;
                    }
                }
            }
        }
        return shortest;
    }

}
