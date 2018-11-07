package com.chukun.datastruct.linkedList;


public class MainRun {

    public static void main(String[] args) {

        DataLinkedList<Integer> linkedList = new DataLinkedList<>();
        for(int i = 0 ; i < 5 ; i ++){
            linkedList.add(i,i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 666);
        System.out.println(linkedList);

        linkedList.remove(2);
        System.out.println(linkedList);
    }
}
