package com.chukun.datastruct.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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

    //查找二分搜索树中的值
    public Node search(E e){
        return getValue(root,e);
    }

    /**
     * 查找二分搜索树中的节点
     * @param node
     * @param e
     * @return
     */
    private Node getValue(Node node,E e) {
        if(node==null){
            return null;
        }
        if(e.compareTo(node.e)<0){
            return getValue(node.left,e);
        }else if(e.compareTo(node.e)>0){
            return getValue(node.right,e);
        }else{
            return node;
        }
    }

    /**
     * 是否包含值为e的node
     * @param e
     * @return
     */
    public boolean containsValue(E e){
        return  getValue(root,e)!=null;
    }

    /**
     * 先序遍历
     */
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node) {
        if(node==null){
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历
     */
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node) {
        if(node==null){
            return;
        }
        preOrder(node.left);
        System.out.println(node.e);
        preOrder(node.right);
    }

    /**
     * 后序遍历
     */
    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node) {
        if(node==null){
            return;
        }
        preOrder(node.left);
        preOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 先序遍历的非递归写法
     */
    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        if(root ==null){
            return;
        }
        stack.push(root);
        while(!stack.isEmpty()){
            //记录当前需要访问的节点
            Node currentNode = stack.pop();
            System.out.println(currentNode.e);
            if(currentNode.right!=null){
                stack.push(currentNode.right);
            }
            if(currentNode.left!=null){
                stack.push(currentNode.left);
            }
        }
    }

    /**
     * 二分搜索树的层级遍历
     */
    public void levelOrder(){
        List<Node> list = new LinkedList<>();
        if(root==null){
            return;
        }
        list.add(root);
        while(!list.isEmpty()){
            Node currentNode = ((LinkedList<Node>) list).remove();
            System.out.println(currentNode.e);
            if(currentNode.left!=null){
                list.add(currentNode.left);
            }
            if(currentNode.right!=null){
                list.add(currentNode.right);
            }
        }
    }

    /**
     * 删除二分搜索树的一个节点
     */
    public void remove(E e){
         root =  remove(root,e);
    }
    // 删除掉以node为根的二分搜索树中值为e的节点, 递归算法
    // 返回删除节点后新的二分搜索树的根
    private Node remove(Node node,E e){
        if(node==null){
            return null;
        }
        if(e.compareTo(node.e)<0){
            node.left = remove(node.left,e);
            return node;
        }else if(e.compareTo(node.e)>0){
            node.right = remove(node.right,e);
            return node;
        }else{
            //node.e 与 e相等

            // 待删除节点左子树为空的情况
            if(node.left==null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            // 待删除节点右子树为空的情况
            if(node.right==null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            // 待删除节点左右子树均不为空的情况
            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minNum(node.right);//获取后继节点
            successor.right = removeMin(node.right);//返回删除最小元素之后的根节点
            successor.left = node.left;//赋值左子树节点
            node.left = node.right=null;//将node从二分搜索树中断掉
            return successor;//返回新的删除之后的根节点
        }
    }

    /**
     * 找到二分搜索树中，元素的最小值
     */
    public E minNum(){
        if(size==0){
            throw new IllegalArgumentException("BinarySearchTree is empty");
        }
        Node minNode = minNum(root);
        return minNode.e;
    }

    private Node minNum(Node node) {
        if(node.left==null){
            return node;
        }
        return minNum(node.left);
    }

    /**
     * 找到二分搜索树中，元素的最大值
     */
    public E maxNum(){
        if(size==0){
            throw new IllegalArgumentException("BinarySearchTree is empty");
        }
        Node minNode = maxNum(root);
        return minNode.e;
    }

    private Node maxNum(Node node) {
        if(node.right==null){
            return node;
        }
        return minNum(node.right);
    }

    /**
     *  删除掉以node为根的二分搜索树中的最小节点,返回最小的值
     */
    public E removeMin(){
        E ret = minNum();
        root = removeMin(root);
        return ret;
    }

    /**
     * 删除掉以node为根的二分搜索树中的最小节点
     * 返回删除节点后新的二分搜索树的根
     * @param node
     * @return
     */
    private Node removeMin(Node node){
        if(node.left==null){
            Node nodeRight = node.right;
            node.right=null;
            size--;
            return nodeRight;
        }
        node.left= removeMin(node.left);
        return node;
    }

    /**
     * 删除掉以node为根的二分搜索树中的最大节点,返回最大的值
     */
    public E removeMax(){
        E ret = maxNum();
        root = removeMax(root);
        return ret;
    }

    /**
     * 删除掉以node为根的二分搜索树中的最大节点
     * 返回删除节点后新的二分搜索树的根
     * @param node
     * @return
     */
    private Node removeMax(Node node){
        if(node.right==null){
            Node nodeLeft = node.left;
            node.left=null;
            size--;
            return nodeLeft;
        }
        node.right= removeMax(node.right);
        return node;
    }

}
