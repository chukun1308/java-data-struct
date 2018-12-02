package com.chukun.datastruct.tree;

import java.util.TreeMap;

/**
 * 字典树的基本操作
 */
public class Trie {

    /**
     * 定义字典树的基本数据类型
     */
    private class Node{
        private boolean isWord;
        private TreeMap<Character,Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }
        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;
    public Trie(){
        root = new Node();
        size=0;
    }

    public int getSize(){
        return size;
    }

    /**
     * 创建字典树
     * @param word
     */
    public void buildTrie(String word){
        Node currentNode = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(currentNode.next.get(c)==null){
              currentNode.next.put(c,new Node());
            }
            currentNode = currentNode.next.get(c);
        }
        if(!currentNode.isWord){
            currentNode.isWord = true;
            size++;
        }
    }

    /**
     * 判断字段树中是否包含某个单词
     * @param word
     * @return
     */
    public boolean contains(String word){
        Node currentNode = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(currentNode.next.get(c)==null){
                return false;
            }
            currentNode = currentNode.next.get(c);
        }
        return currentNode.isWord;
    }

    /**
     * 判断字典树中是否包含某个前缀
     * @param prefix
     * @return
     */
    public boolean prefix(String prefix){
        Node currentNode = root;
        for(int i=0;i<prefix.length();i++){
            char c = prefix.charAt(i);
            if(currentNode.next.get(c)==null){
                return false;
            }
            currentNode= currentNode.next.get(c);
        }
        return true;
    }
}
