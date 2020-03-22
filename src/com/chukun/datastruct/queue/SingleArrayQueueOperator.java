package com.chukun.datastruct.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author  chukun
 * 单向队列的基本操作
 */
public class SingleArrayQueueOperator {

    public static void main(String[] args) throws IOException {
        System.out.println("=======================测试单向队列=====================");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayQueue arrayQueue = new ArrayQueue();
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
                    arrayQueue.enqueue(value);
                    break;
                case "d":
                    try {
                        int val = arrayQueue.dequeue();
                        System.out.println("出队元素是 = "+val);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "h":
                    try {
                        int val = arrayQueue.headQueue();
                        System.out.println("队首元素是 = "+val);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "s":
                    arrayQueue.listQueue();
                    break;
                case "e":
                    loop = false;
                    break;
            }
        }
    }
}

class ArrayQueue{

    private int front;//队首前一个位置
    private int rear;//队尾前一个位置
    private int maxSize;//队列元素最大个数
    private int [] arrayQueue;

    public ArrayQueue(){
        this(5);
    }

    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        this.front = -1;
        this.rear = -1;
        this.arrayQueue = new int[maxSize];
    }

    public boolean isFull(){
        return this.rear == maxSize-1;
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
        this.rear++;
        arrayQueue[this.rear] = value;
    }

    /**
     * 出队操作
     * @return
     */
    public int dequeue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空,无元素出队.....");
        }
        this.front++;
        return arrayQueue[this.front];
    }

    /**
     * 获取队首元素
     * @return
     */
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空,无队首元素.....");
        }
        return arrayQueue[this.front+1];
    }

    /**
     * 遍历数组的元素
     */
    public void listQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空,无元素遍历.....");
        }
        for(int i=front+1;i<rear+1;i++){
            System.out.printf("array[%d]=%d\n",i,arrayQueue[i]);
        }
    }
}
