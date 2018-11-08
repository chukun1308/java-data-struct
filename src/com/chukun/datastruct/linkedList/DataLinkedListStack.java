package com.chukun.datastruct.linkedList;

import com.chukun.datastruct.stack_queue.DataStack;

/**
 * 基于链表实现栈
 * 由于链表在头结点做插入，删除操作，时间复杂度为O(1)
 * 适合用作实现栈的操作
 */
public class DataLinkedListStack<E> implements DataStack<E> {

    private DataLinkedList<E> dataLinkedList;

    public DataLinkedListStack(){
        dataLinkedList = new DataLinkedList<>();
    }

    @Override
    public int getSize() {
        return dataLinkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return dataLinkedList.isEmpty();
    }

    @Override
    public void push(E e) {
        dataLinkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return dataLinkedList.removeFirst();
    }

    @Override
    public E peek() {
        return dataLinkedList.getFirst();
    }
}
