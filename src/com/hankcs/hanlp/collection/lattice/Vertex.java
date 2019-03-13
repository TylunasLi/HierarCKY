package com.hankcs.hanlp.collection.lattice;

/**
 * 
 * 词图中的顶点
 * 词图是一个有向无环图，入度为零和出度为零的顶点有且仅各有一个
 * 顶点均为序列中的片段.
 * @author TylunasLi
 *
 */
public abstract class Vertex {
    
    /**
     *  正向 Viterbi过程中上一个节点
     */
    public Vertex from;
    
    /**
     *  逆向 Viterbi过程中下一个节点
     */
    public Vertex to;
    
    /**
     *  正向 Viterbi过程的当前节点累计总权重
     */
    public double totalWeightFrom;

    /**
     *  逆向 Viterbi过程的当前节点累计总权重
     */
    public double totalWeightTo;

    /**
     *  自身权重，通过特征向量累加得到
     */
    public double selfWeight;

    /**
     * 返回顶点的长度
     * @return
     */
    public abstract int length();

    /**
     * 返回顶点的标签
     * @return
     */
    public abstract String getTag();
    
    public static class SimpleVertex extends Vertex {
        
        String tag;
        
        public SimpleVertex(String tag) {
            this.tag = tag;
        }
        public int length() {
            return 1;
        }
        @Override
        public String getTag() {
            return tag;
        }
        
        @Override
        public String toString() {
            return String.format("tag:%s", tag);
        }
    }
    
    public static Vertex createFirstNode() {
        return new SimpleVertex("始##始");
    }

    public static Vertex createLastNode() {
        return new SimpleVertex("末##末");
    }
    
    

}
