package com.chukun.datastruct.stack_queue;

import com.chukun.datastruct.array.DynamicArray;

/**
 * 基于数组实现队列
 * @param <E>
 */
public class ArrayDataQueue<E> implements DataQueue<E> {

    private  DynamicArray<E> dynamicArray;


    public ArrayDataQueue(int capacity){
        dynamicArray = new DynamicArray<>(capacity);
    }

    public ArrayDataQueue(){
        dynamicArray = new DynamicArray<>();
    }
    @Override
    public int getSize() {
        return dynamicArray.getSize();
    }

    @Override
    public boolean isEmpty() {
        return dynamicArray.isEmpty();
    }

    @Override
    public void enqueue(E e) {
      dynamicArray.addLast(e);
    }

    @Override
    public E dequeue() {
        return dynamicArray.removeFirst();
    }

    @Override
    public E getFront() {
        return dynamicArray.getFirst();
    }
}
