package com.chukun.datastruct.stack_queue;

import com.chukun.datastruct.array.DynamicArray;

public class ArrayDataStack<E> implements DataStack<E> {

    private DynamicArray<E> dynamicArray;

    public ArrayDataStack(){
        dynamicArray = new DynamicArray();
    }

    public ArrayDataStack(int capacity){
        dynamicArray = new DynamicArray(capacity);
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
    public void push(E e) {
        dynamicArray.addLast(e);
    }

    @Override
    public E pop() {
        return dynamicArray.removeLast();
    }

    @Override
    public E peek() {
        return dynamicArray.getLast();
    }
}
