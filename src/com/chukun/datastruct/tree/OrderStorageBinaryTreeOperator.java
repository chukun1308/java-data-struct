package com.chukun.datastruct.tree;

/**
  *@auther : chukun
  *@description : 顺序存储二叉树
  *@create : 2019/7/6 22:56
  *@copyright www.hualala.com
  */
public class OrderStorageBinaryTreeOperator {

    public static void main(String[] args) {

        int [] array = {1,2,3,4,5,6,7};
        OrderStorageBinaryTree orderStorageBinaryTree = new OrderStorageBinaryTree();
        System.out.println("先序遍历");
        orderStorageBinaryTree.preOrder(array);

        System.out.println("中序遍历");
        orderStorageBinaryTree.infixOrder(array);

        System.out.println("后序遍历");
        orderStorageBinaryTree.postOrder(array);
    }
}

/**
 * 顺序二叉树
 */
class OrderStorageBinaryTree{

    public void preOrder(int[] array){
        preOrder(array,0);
    }

    public void infixOrder(int[] array){
        infixOrder(array,0);
    }

    public void postOrder(int[] array){
        postOrder(array,0);
    }


    /**
     * 顺序二叉树，先序遍历
     * @param array
     * @param index
     */
    public void preOrder(int[] array,int index){
        if(array==null || array.length==0){
            System.out.println("数据为空，不能先序遍历");
            return;
        }
        if(index<0 || index>array.length-1){
            System.out.println("需要遍历的下标错误");
            return;
        }
        System.out.println(array[index]);
        if((2*index+1)<array.length-1){
            preOrder(array,2*index+1);
        }
        if((2*index+2)<array.length){
            preOrder(array,2*index+2);
        }
    }

    /**
     * 顺序二叉树，中序遍历
     * @param array
     * @param index
     */
    public void infixOrder(int[] array,int index){
        if(array==null || array.length==0){
            System.out.println("数据为空，不能先序遍历");
            return;
        }
        if(index<0 || index>array.length-1){
            System.out.println("需要遍历的下标错误");
            return;
        }
        if((2*index+1)<array.length-1){
            infixOrder(array,2*index+1);
        }
        System.out.println(array[index]);
        if((2*index+2)<array.length){
            infixOrder(array,2*index+2);
        }
    }

    /**
     * 顺序二叉树，后序遍历
     * @param array
     * @param index
     */
    public void postOrder(int[] array,int index){
        if(array==null || array.length==0){
            System.out.println("数据为空，不能先序遍历");
            return;
        }
        if(index<0 || index>array.length-1){
            System.out.println("需要遍历的下标错误");
            return;
        }
        if((2*index+1)<array.length-1){
            postOrder(array,2*index+1);
        }
        if((2*index+2)<array.length){
            postOrder(array,2*index+2);
        }
        System.out.println(array[index]);
    }
}
