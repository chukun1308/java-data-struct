package com.chukun.datastruct.heap;

import com.chukun.datastruct.stack_queue.DataQueue;

/**
 * 基于最大堆的优先队列
 * @param <E>
 */
public class PriorityQueue<E extends Comparable<E>> implements DataQueue<E> {

    private MaxHeap<E> maxHeap;

    public PriorityQueue(){
        maxHeap = new MaxHeap<>();
    }

    @Override
    public int getSize(){
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty(){
        return maxHeap.isEmpty();
    }

    @Override
    public E getFront(){
        return maxHeap.getMaxData();
    }

    @Override
    public void enqueue(E e){
        maxHeap.add(e);
    }

    @Override
    public E dequeue(){
        return maxHeap.exactMaxData();
    }
}
