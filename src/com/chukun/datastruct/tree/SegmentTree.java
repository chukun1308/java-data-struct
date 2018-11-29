package com.chukun.datastruct.tree;

import com.chukun.datastruct.inf.MergeOperator;

/**
 * @author chukun
 * 线段树的基本操作
 */
public class SegmentTree<E> {

    private E[] tree;
    private E[] data;

    private MergeOperator<E> mergeOperator;

    public SegmentTree(E[] array,MergeOperator<E> mergeOperator){
        this.mergeOperator = mergeOperator;
        data = (E[]) new Object[array.length];
        for(int i=0;i<array.length;i++){
            data[i] = array[i];
        }
        tree = (E[]) new Object[4*array.length];
        buildSegmentTree(0,0,array.length-1);
    }

    /**
     * 创建线段树
     * @param treeIndex
     * @param left
     * @param right
     */
    private void buildSegmentTree(int treeIndex, int left, int right) {
        if(left==right){
            tree[treeIndex] = data[left];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = left+(right-left)/2;
        //创建左边的线段树
        buildSegmentTree(leftTreeIndex,left,mid);
        //创建右边的线段树
        buildSegmentTree(rightTreeIndex,mid+1,right);

        tree[treeIndex] = mergeOperator.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }

    public int getSize(){
        return data.length;
    }
    public E get(int index){
        if(index<0 || index>=data.length){
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return 2*index+1;
    }
    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return 2*index+2;
    }

    /**
     * 查找区间树的元素结果
     * @param queryL
     * @param queryR
     * @return
     */
    public E query(int queryL,int queryR){
        if(queryL<0 || queryL>=data.length || queryR<0 || queryR>=data.length || queryL>queryR){
            throw new IllegalArgumentException("queryL index or queryR not valid");
        }
        return query(0,0,data.length-1,queryL,queryR);
    }

    /**
     * 内部辅助查找函数
     * @param treeIndex
     * @param l
     * @param r
     * @param queryL
     * @param queryR
     * @return
     */
    private E query(int treeIndex,int l,int r,int queryL,int queryR){
        if(l==queryR || r==queryR){
            return tree[treeIndex];
        }
        int mid = l+(r-l)/2;
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex= rightChild(treeIndex);

        if(queryL>=mid+1){
            //查找的区间在右边
           return query(rightChildIndex,mid+1,r,queryL,queryR);
        }else if(queryR<=mid){
            //查找的区间在左边
            return query(leftChildIndex,l,mid,queryL,queryR);
        }
        //查找的区间横跨左右区间
        E leftResult = query(treeIndex,l,mid,queryL,mid);
        E rightResult = query(treeIndex,mid+1,r,mid+1,queryR);
        return mergeOperator.merge(leftResult,rightResult);
    }


}
