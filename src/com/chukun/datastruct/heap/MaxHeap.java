package com.chukun.datastruct.heap;

import com.chukun.datastruct.array.DynamicArray;

/**
 * @author chukun
 * 最大堆的基本操作
 * 堆的性质:
 * parent节点的索引为  (index-1)/2
 * leftChild节点的索引为 (2*index)+1
 * rightChild节点的索引为 (2*index)+2
 */
public class MaxHeap <E extends Comparable<E>>{

    private DynamicArray<E> data;

    public MaxHeap(int capacity){
        data = new DynamicArray<>(capacity);
    }
    public MaxHeap(){
        data = new DynamicArray<>();
    }
    //返回堆中元素的个数
    public int size(){
        return data.getSize();
    }
    //判断堆是否为空
    public boolean isEmpty(){
        return data.isEmpty();
    }
    //给定一个索引index，返回其父节点的索引
    public int parent(int index){
        if(index<1){
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index-1)/2;
    }

    //给定一个索引index，返回其左子树节点的索引
    public int left(int index){
        return (2*index)+1;
    }

    //给定一个索引index，返回其左子树节点的索引
    public int right(int index){
        return (2*index)+2;
    }

    //给堆中添加元素
    public void add(E e){
        data.addLast(e);
        //上浮操作
        siftUp(data.getSize()-1,e);
    }

    /**
     * 上浮操作
     * @param index
     * @param e
     */
    private void siftUp(int index,E e){
        int parent = parent(index);
        int currentIndex = index;
        while(currentIndex>0 && data.get(parent).compareTo(data.get(currentIndex))<0){
            //交换当前节点与父节点的位置
            data.swap(parent,currentIndex);
            currentIndex = parent;
            parent = parent(currentIndex);
        }
    }
}
