/**
 * Copyright 2017-2019 TylunasLi @ Nanjing Yunwen Network Co. Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hankcs.hanlp.collection.trie.bintrie;

import com.hankcs.hanlp.collection.trie.bintrie.BaseNode.Status;

/**
 * 模仿DoubleArrayTrie的Searcher
 * @author TylunAsLi
 *
 * @param <V>
 */
public class BinTrieSearcher<V> {

	/** 
	 * Trie树
	 */
	private BinTrie<V> trie;
    /**
     * 传入的字符数组
     */
    private char[] charArray;
    
    /**
     * key的起点
     */
    public int begin;
    
    /**
     * key的长度
     */
    public int length;
    
    /**
     * 上一个node位置
     */
    private BaseNode<V> last;
    /**
     * key对应的value
     */
    public V value;
    
    /**
     * 上一个字符的下标
     */
    private int i;
    
	
	public BinTrieSearcher(BinTrie<V> trie, int offset, char [] sentence) {
		this.trie = trie;
		this.charArray = sentence;
		last = trie;
        i = offset;
        if (charArray.length == 0) begin = -1;
        else begin = offset;
	}
	
    /**
     * 取出下一个命中输出
     *
     * @return 是否命中，当返回false表示搜索结束，否则使用公开的成员读取命中的详细信息
     */
	public boolean next() {
		BaseNode<V> node = last;

        for (; ; ++i) {
            if (i == charArray.length) {
                ++begin;         	                // 指针到头了，将起点往前挪一个，重新开始，状态归零
                if (begin == charArray.length) break;
                i = begin;
                node = trie;
            }
            node = node.getChild(charArray[i]);     // 状态转移
            if (node == null || node.status == Status.UNDEFINED_0) {
                i = begin;                          // 转移失败，也将起点往前挪一个，重新开始，状态归零
                ++begin;
                if (begin == charArray.length) break;
                node = trie;
                continue;
//            } else {                                // 转移成功
//                ;
            }
//            ;
            if (node.status == Status.WORD_MIDDLE_2 || node.status == Status.WORD_END_3) {
                length = i - begin + 1;	            // 查到一个词
                value = node.getValue();
                last = node;						// 即使后面没有词，也会统一循环处理
                ++i;
                return true;
            }
        }
		return false;
	}
	
}
