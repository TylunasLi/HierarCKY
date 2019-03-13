package com.hankcs.hanlp.collection.lattice;

/**
 * 
 * 序列划分有向无环图中的节点
 * @author TylunasLi
 *
 */
public class CharVertex<T> extends Vertex {
    
    public static final String BASE_TAG = "O";

    /** 文本 */
    protected String text;

    /** 节点对应的标签 */
    protected String tag;
    
    /**
     * 值对象
     */
    public T value;
    
    public CharVertex(String text) {
        this.text = text;
        tag = BASE_TAG;
    }

    public CharVertex(String text, String tag) {
        this.text = text;
        this.tag = tag;
    }

    public CharVertex(String text, String tag, T value) {
        this.text = text;
        this.tag = tag;
        this.value = value;
    }

    public static CharVertex<?> createFirstNode() {
        return new CharVertex("始##始") {
            public int length() {
                return 1;
            }
        };
    }

    public static CharVertex<?> createLastNode() {
        return new CharVertex("终##终") {
            public int length() {
                return 1;
            }
        };
    }

    
    public int length() {
        return text.length();
    }

    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }

    public Vertex getFrom() {
        return from;
    }

    public Vertex getTo() {
        return to;
    }

    public double getTotalWeightFrom() {
        return totalWeightFrom;
    }

    public double getTotalWeightTo() {
        return totalWeightTo;
    }

    public double getSelfWeight() {
        return selfWeight;
    }

    public String getText() {
        return text;
    }

    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getClassedWord() {
        if (BASE_TAG.equals(tag))
            return text;
        return tag;
    }

    @Override
    public String toString() {
        if (value != null)
            return String.format("%s/%s %s", text, tag, value);
        return String.format("%s/%s", text, tag);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CharVertex other = (CharVertex) obj;
        if (tag == null) {
            if (other.tag != null)
                return false;
        } else if (!tag.equals(other.tag))
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

}
