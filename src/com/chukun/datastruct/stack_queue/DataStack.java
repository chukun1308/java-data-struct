package com.chukun.datastruct.stack_queue;

public interface DataStack<E> {

    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();
}
