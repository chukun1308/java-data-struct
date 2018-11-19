package com.chukun.datastruct.tree;

/**
 * @author chukun
 * 定义一个基本的二分搜索树
 */
public class BinarySearchTree<E extends Comparable<E>>{

    private Node root;
    private int size;

    //定义基本的数据类型
    private class Node{
        private E e;
        private Node left,right;

        public Node(E e){
            this.e=e;
            this.left=null;
            this.right=null;
        }
    }

    public  BinarySearchTree(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    /**
     * 向二凤搜索树中插入元素
     * @param e
     */
    public void add(E e){
//        if(root==null){
//            root = new Node(e);
//        }else {
//            add(root,e);
//        }
        add(root,e);
    }

    /**
     * 向以node为根的二分搜索树中插入元素e，递归算法
     * @param node
     * @param e

    private void add(Node node, E e) {
         if(e.equals(node.e)){
             return;
         }
         if(e.compareTo(node.e)<0 && node.left==null){
             node.left = new Node(e);
             size++;
             return;
         }else if(e.compareTo(node.e)>0 && node.right==null){
             node.right = new Node(e);
             size++;
             return;
         }
         if(e.compareTo(node.e)<0){
             add(node.left,e);
         }else {
             add(node.right,e);
         }
    }*/
    // 向以node为根的二分搜索树中插入元素e，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node,E e){
        if(null==node){
            size++;
            return new Node(e);
        }
        if(e.compareTo(node.e)<0){
            node.left = add(node.left,e);
        }else if(e.compareTo(node.e)>0){
            node.right = add(node.right,e);
        }
        return node;
    }


}
