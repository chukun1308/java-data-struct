package com.chukun.datastruct.stack_queue;

public interface DataQueue<E> {

    int getSize();
    boolean isEmpty();
    void enqueue(E e);
    E dequeue();
    E getFront();
}
