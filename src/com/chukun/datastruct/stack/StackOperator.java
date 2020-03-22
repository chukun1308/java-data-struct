package com.chukun.datastruct.stack;

import java.util.Optional;

/**
 * @author chukun
 * 栈的基本操作
 */
public class StackOperator {

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);
        arrayStack.push(5);
        arrayStack.push(8);
        arrayStack.push(15);
        arrayStack.push(18);
        arrayStack.push(22);
        System.out.println("入栈遍历");
        arrayStack.listStack();

        arrayStack.pop();
        System.out.println("弹栈遍历");
        arrayStack.listStack();
    }
}

class ArrayStack{
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull(){
        return top==maxSize-1;
    }

    public boolean isEmpty(){
        return top==-1;
    }

    /**
     * 获取栈顶的元素
     * @return
     */
    public int peek(){
        return stack[top];
    }

    /**
     * 压栈
     * @param value
     */
    public void push(int value){
        if(isFull()){
            System.out.println("stack is full,can not push the value......");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 弹栈
     * @return
     */
    public int pop(){
        int retValue = stack[top];
        stack[top] =0;
        top--;
        return retValue;
    }

    /**
     * 遍历栈
     */
    public void listStack(){
        if(isEmpty()){
            System.out.println("stack is empty,can not operator list......");
            throw new RuntimeException("stack is empty");
        }
        for(int i=top;i>=0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}
