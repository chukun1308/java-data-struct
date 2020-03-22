package com.chukun.datastruct.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
  *@auther : chukun
  *@description : 赫夫曼树的基本实现
  *@create : 2019/7/10 0:05
  *@copyright www.hualala.com
  */
public class HuffmanTree {

    public static void main(String[] args) {
        int [] array = {8,5,4,2,23};
        Node root = buildHuffmanTree(array);
        if(root!=null) {
            preOrder(root);
        }
    }

    public static void preOrder(Node root){
        if(root==null){
            return;
        }
        System.out.println(root);
        if(root.getLeft()!=null){
            preOrder(root.getLeft());
        }
        if(root.getRight()!=null){
            preOrder(root.getRight());
        }
    }

    /**
     * 构建一棵赫夫曼树
     * @param array
     */
    public static Node buildHuffmanTree(int [] array){
        if(array==null || array.length==0){
            return null;
        }
        List<Node> nodes = new ArrayList<>();
        for(int value:array){
            nodes.add(new Node(value));
        }
        while (nodes.size()>1) {
            //排序
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(leftNode.getValue() + rightNode.getValue());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);
            //之后删除left right节点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //在将当前的parent节点放入到nodes中，然后排序
            nodes.add(parent);
        }
        return nodes.get(0);
    }

}

class Node implements Comparable<Node>{

    private int value;
    private Node left;
    private Node right;

    public Node() {}

    public Node(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value-o.getValue();
    }
}
