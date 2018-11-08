package com.chukun.datastruct.linkedList;

import com.chukun.datastruct.stack_queue.DataQueue;

/**
 * 基于链表实现队列
 * 由于队列的操作需要两端操作，但是两端操作，链表的时间复杂度在尾部操作，删除元素，必为O(n)
 * 所以，考虑在头结点位置做出队列的操作，在尾节点做删除操作，这样就需要一个节点，时刻记录尾节点的位置
 * 这样在尾节点做入队操作，时间复杂就为O(n)
 */
public class DataLinkedListQueue<E> implements DataQueue<E> {

    private Node head,tail;
    private int size;

    public DataLinkedListQueue(){
        head = null;
        tail = null;
        size=0;
    }
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public void enqueue(E e) {
        if(tail==null){
            //此时队列为空
            tail= new Node(e);
            head = tail;
        }else{
            //链表不为空
            Node node = new Node(e);
            tail.next = node;
            tail = node;
        }

    }

    @Override
    public E dequeue() {
        if(isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }

        Node retNode = head;
        head = head.next;
        retNode.next=null;
        if(head==null){
            //此时只有一个元素,需要维护一下尾节点
            tail = null;
        }
        size--;
        return retNode.e;
    }

    @Override
    public E getFront() {
        if(isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return head.e;
    }

    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }
}
