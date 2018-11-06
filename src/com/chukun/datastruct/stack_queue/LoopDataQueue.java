package com.chukun.datastruct.stack_queue;

/**
 * 循环队列
 * 队列为空的条件 front==tail
 * 队列满了的条件  (tail+1)%data.size==front
 * 计算front与tail下一个位置：front = (front+1)%data.length tail = (tail+1)%data.length
 * @param <E>
 */
public class LoopDataQueue<E> implements DataQueue<E> {

    private E[] array;
    private int front;
    private int tail;
    private int size;

    public LoopDataQueue(int capacity){
        array = (E[]) new Object[capacity+1];//默认会浪费一个空间
        front=0;
        tail=0;
        size=0;
    }
    public LoopDataQueue(){
        this(10);//默认会浪费一个空间
    }

    public int getCapacity(){
        return array.length-1;
    }
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return tail==front;
    }

    /**
     * 入队的操作
     * @param e
     */
    @Override
    public void enqueue(E e) {
        //先判断队列是否已经满了
        if((tail+1)%array.length==front){
            //队列已经满了，需要做扩容操作
            resize(array.length*2);
        }
        array[tail+1] = e;
        //计算当前tail的值
        tail = (tail+1)%array.length;
        size++;
    }

    /**
     * 出队的操作
     * @return
     */
    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        E e = array[front];
        array[front] = null;
        //计算当前front的值
        front = (front+1)%array.length;
        size--;
        if(size==getCapacity()/4 && getCapacity()/2 !=0){
            resize(array.length/2);
        }
        return e;
    }

    @Override
    public E getFront() {
        return array[front];
    }

    /**
     * 扩容操作
     * @param newCapacity
     */
    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity+1];
        for(int i=0;i<array.length;i++){
            newData[i]=array[(front+1)%array.length];
        }
        array = newData;
        front = 0;
        tail = size;
    }
}
