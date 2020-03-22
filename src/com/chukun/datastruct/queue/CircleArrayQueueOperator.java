package com.chukun.datastruct.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author chukun
 * 环形队列的数组实现
 */
public class CircleArrayQueueOperator {

    public static void main(String[] args) throws IOException {
        System.out.println("=======================测试环形队列=====================");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue();
        boolean loop = true;
        while(loop){
            System.out.println("请输入你的操作");
            System.out.println("a(入队)");
            System.out.println("d(出队)");
            System.out.println("h(获取队首元素)");
            System.out.println("s(遍历队列)");
            System.out.println("e(退出)");
            String operator = br.readLine();
            switch (operator){
                case "a":
                    System.out.println("请输入要入队的元素......");
                    int value = Integer.parseInt(br.readLine());
                    circleArrayQueue.enqueue(value);
                    break;
                case "d":
                    try {
                        int val = circleArrayQueue.dequeue();
                        System.out.println("出队元素是 = "+val);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "h":
                    try {
                        int val = circleArrayQueue.headQueue();
                        System.out.println("队首元素是 = "+val);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "s":
                    circleArrayQueue.listQueue();
                    break;
                case "e":
                    loop = false;
                    break;
            }
        }
    }
}

class CircleArrayQueue{

    private int front;//队首前一个位置
    private int rear;//队尾前一个位置
    private int maxSize;//队列元素最大个数
    private int [] arrayQueue;

    public CircleArrayQueue(){
        this(5);
    }

    public CircleArrayQueue(int maxSize){
        this.maxSize = maxSize;
        this.front = 0;//指向队首的元素
        this.rear = 0;//指向队首的元素
        this.arrayQueue = new int[maxSize];
    }

    public boolean isFull(){
        return (this.rear+1)%this.maxSize == this.front;
    }

    public boolean isEmpty(){
        return this.front == this.rear;
    }

    /**
     * 入队操作
     * @param value
     */
    public void enqueue(int value){
        if(isFull()){
            System.out.println("队列已满，无法加入......");
            return;
        }
        arrayQueue[this.rear] = value;
        this.rear = (this.rear+1)%this.maxSize;
    }

    /**
     * 出队操作
     * @return
     */
    public int dequeue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空,无元素出队.....");
        }
        int val = arrayQueue[this.front];
        this.front = (this.front+1)%this.maxSize;
        return val;
    }

    /**
     * 获取队首元素
     * @return
     */
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空,无队首元素.....");
        }
        return arrayQueue[this.front%this.maxSize];
    }

    /**
     * 遍历数组的元素
     */
    public void listQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空,无元素遍历.....");
        }
        int actualSize = actualSize();
        for(int i=this.front;i<this.front+actualSize;i++){
            System.out.printf("array[%d]=%d\n",i%maxSize,arrayQueue[i%maxSize]);
        }
    }

    /**
     * 环形队列中实际的元素个数
     * @return
     */
    public int actualSize(){
        /**
         * 探究为什么环形数组中实际的元素个数是下面的表达式???
         * 举例
         * real = 2
         * front = 1
         * maxSize = 5;
         * 此时数组的元素应该是一个
         */
        return (this.rear+this.maxSize-this.front)%this.maxSize;
    }
}


