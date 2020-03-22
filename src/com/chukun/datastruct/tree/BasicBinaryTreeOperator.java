package com.chukun.datastruct.tree;

import java.util.Optional;

/**
  *@auther : chukun
  *@description : 二叉树的基本操作
  *@create : 2019/7/5 23:49
  *@copyright www.hualala.com
  */
public class BasicBinaryTreeOperator {

    public static void main(String[] args) {
        BasicBinaryTree basicBinaryTree = new BasicBinaryTree();
        BasicTreeNode root = new BasicTreeNode(1,"aaa");
        BasicTreeNode node = new BasicTreeNode(2,"bbb");
        BasicTreeNode node2 = new BasicTreeNode(3,"ccc");
        BasicTreeNode node3 = new BasicTreeNode(4,"ddd");
        BasicTreeNode node4 = new BasicTreeNode(5,"eee");

        root.setLeft(node);
        root.setRight(node2);
        node2.setLeft(node3);
        node2.setRight(node4);
        basicBinaryTree.setRoot(root);

        //先序遍历
        System.out.println("先序遍历");
        basicBinaryTree.preOrder();

        //中序遍历
//        System.out.println("中序遍历");
//        basicBinaryTree.infixOrder();
//
//        //后序遍历
//        System.out.println("后序遍历");
//        basicBinaryTree.postOrder();
//
//        //先序查找
//        System.out.println("先序查找");
//        Optional<BasicTreeNode> optional = basicBinaryTree.preOrderSearch(5);
//        if(optional!=null){
//            System.out.println("先序查找找到了");
//        }else{
//            System.out.println("先序查找没找到");
//        }
//
//        System.out.println("中序查找");
//        Optional<BasicTreeNode> infixOptional = basicBinaryTree.infixOrderSearch(5);
//        if(infixOptional!=null){
//            System.out.println("中序查找找到了");
//        }else{
//            System.out.println("中序查找没找到");
//        }
//
//        System.out.println("后序查找");
//        Optional<BasicTreeNode> postOptional = basicBinaryTree.postOrderSearch(5);
//        if(postOptional!=null){
//            System.out.println("后序查找找到了");
//        }else{
//            System.out.println("后序查找没找到");
//        }
//        basicBinaryTree.delNode(1);
//        System.out.println("删除之后");
//        if(basicBinaryTree.getRoot()!=null) {
//            basicBinaryTree.preOrder();
//        }
    }
}

class BasicBinaryTree{

    private BasicTreeNode root;

    public BasicTreeNode getRoot() {
        return root;
    }

    public void setRoot(BasicTreeNode root) {
        this.root = root;
    }

    /**
     * 先序遍历
     */
    public void preOrder(){
        root.preOrder(root);
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        root.infixOrder(root);
    }

    /**
     * 后序遍历
     */
    public void postOrder(){
        root.postOrder(root);
    }

    /**
     * 先序查找
     */
    public Optional<BasicTreeNode> preOrderSearch(int no){
        return root.preOrderSearch(root, no);
    }

    /**
     * 中序查找
     */
    public Optional<BasicTreeNode> infixOrderSearch(int no){
        return root.infixOrderSearch(root, no);
    }

    /**
     * 后序查找
     */
    public Optional<BasicTreeNode> postOrderSearch(int no){
        return root.postOrderSearch(root, no);
    }

    /**
     * 删除节点
     * @param no
     */
    public void delNode(int no){
        if(root==null){
            System.out.println("二叉树为空,不支持删除操作");
            return;
        }else{
            if(root.getNo()==no){
                //删除整个二叉树
                root = null;
            }else{
                root.delNode(no);
            }
        }
    }
}

class BasicTreeNode{

    private int no;
    private String name;
    private BasicTreeNode left;
    private BasicTreeNode right;

    public BasicTreeNode(int no, String name) {
        this.no = no;
        this.name = name;
        this.left = this.right=null;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BasicTreeNode getLeft() {
        return left;
    }

    public void setLeft(BasicTreeNode left) {
        this.left = left;
    }

    public BasicTreeNode getRight() {
        return right;
    }

    public void setRight(BasicTreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BasicTreeNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 先序遍历
     * @param root
     */
    public void preOrder(BasicTreeNode root){
        if(root==null){
            System.out.println("二叉树为空,无法进行前序遍历");
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
     * 中序遍历
     * @param root
     */
    public void infixOrder(BasicTreeNode root){
        if(root==null){
            System.out.println("二叉树为空,无法进行前序遍历");
            return;
        }
        if(root.getLeft()!=null){
            infixOrder(root.left);
        }
        System.out.println(root);
        if(root.getRight()!=null){
            infixOrder(root.getRight());
        }
    }

    /**
     * 后序遍历
     * @param root
     */
    public void postOrder(BasicTreeNode root){
        if(root==null){
            System.out.println("二叉树为空,无法进行前序遍历");
            return;
        }
        if(root.getLeft()!=null){
            postOrder(root.left);
        }
        if(root.getRight()!=null){
            postOrder(root.getRight());
        }
        System.out.println(root);
    }

    /**
     * 先序查找
     * @param root
     * @param no
     * @return
     */
    public Optional<BasicTreeNode> preOrderSearch(BasicTreeNode root,int no){
        if(root==null){
            return  null;
        }
        System.out.println("~先序查找~");
        if(root.getNo()==no){
            return Optional.of(root);
        }
        Optional<BasicTreeNode> optional = Optional.empty();
        if(root.getLeft()!=null){
            optional = preOrderSearch(root.getLeft(),no);
        }
        if(optional.isPresent()){
            return optional;
        }
        if(root.getRight()!=null){
            optional = preOrderSearch(root.getRight(),no);
        }
        return optional;
    }

    /**
     * 中序查找
     * @param root
     * @param no
     * @return
     */
    public Optional<BasicTreeNode> infixOrderSearch(BasicTreeNode root,int no){
        if(root==null){
            return  Optional.empty();
        }
        Optional<BasicTreeNode> optional = Optional.empty();
        if(root.getLeft()!=null){
            optional =  infixOrderSearch(root.getLeft(),no);
        }
        if(optional.isPresent()){
            return optional;
        }
        System.out.println("~中序查找~");
        if(root.getNo()==no){
            return  Optional.of(root);
        }
        if(root.getRight()!=null){
            optional= infixOrderSearch(root.getRight(),no);
        }
        return optional;
    }

    /**
     * 后序查找
     * @param root
     * @param no
     * @return
     */
    public Optional<BasicTreeNode> postOrderSearch(BasicTreeNode root,int no){
        if(root==null){
            return  Optional.empty();
        }
        Optional<BasicTreeNode> optional = Optional.empty();
        if(root.getLeft()!=null){
            optional =  postOrderSearch(root.getLeft(),no);
        }
        if(optional.isPresent()){
            return optional;
        }
        if(root.getRight()!=null){
            optional =  postOrderSearch(root.getRight(),no);
        }
        if(optional.isPresent()){
            return optional;
        }
        System.out.println("~后序查找~");
        if(root.getNo()==no){
           return Optional.of(root);
        }
        return optional;
    }

    public void delNode(int no){
       //删除左边的节点
       if(this.left!=null && this.left.getNo()==no){
           this.left=null;
           return;
       }
       //删除右边的节点
       if(this.right!=null && this.right.getNo()==no){
          this.right=null;
          return;
       }
       //不然从左边递归删除
        if(this.left!=null){
            this.left.delNode(no);
        }
        if(this.right!=null){
            this.right.delNode(no);
        }
    }

}
