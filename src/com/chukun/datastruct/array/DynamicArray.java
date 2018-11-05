package com.chukun.datastruct.array;

/**
 * 数组的基本操作
 * @param <E>
 */
public class DynamicArray<E> {

    private E[] dataArray;
    private int size;

    public DynamicArray(){
       this(10);
    }

    public DynamicArray(int capacity){
        dataArray = (E[]) new Object[capacity];
        size = 0;
    }

    // 获取数组的容量
    public int getCapacity(){
        return dataArray.length;
    }

    // 获取数组中的元素个数
    public int getSize(){
        return size;
    }

    // 返回数组是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    // 在index索引的位置插入一个新元素e
    public void add(int index, E e){

        if(index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");

        if(size == dataArray.length) {
            resize(2 * dataArray.length);
        }

        for(int i = size - 1; i >= index ; i --) {
            dataArray[i + 1] = dataArray[i];
        }

        dataArray[index] = e;
        size ++;
    }

    // 向所有元素后添加一个新元素
    public void addLast(E e){
        add(size, e);
    }

    // 在所有元素前添加一个新元素
    public void addFirst(E e){
        add(0, e);
    }

    // 获取index索引位置的元素
    public E get(int index){
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
        return dataArray[index];
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size-1);
    }

    // 修改index索引位置的元素为e
    public void set(int index, E e){
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        }
        dataArray[index] = e;
    }

    // 查找数组中是否有元素e
    public boolean contains(E e){
        for(int i = 0 ; i < size ; i ++){
            if(dataArray[i].equals(e))
                return true;
        }
        return false;
    }

    // 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    public int find(E e){
        for(int i = 0 ; i < size ; i ++){
            if(dataArray[i].equals(e))
                return i;
        }
        return -1;
    }

    // 从数组中删除index位置的元素, 返回删除的元素
    public E remove(int index){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");

        E ret = dataArray[index];
        for(int i = index + 1 ; i < size ; i ++) {
            dataArray[i - 1] = dataArray[i];
        }
        size --;
        dataArray[size] = null; // loitering objects != memory leak

        if(size == dataArray.length / 4 && dataArray.length / 2 != 0)
            resize(dataArray.length / 2);
        return ret;
    }

    // 从数组中删除第一个元素, 返回删除的元素
    public E removeFirst(){
        return remove(0);
    }

    // 从数组中删除最后一个元素, 返回删除的元素
    public E removeLast(){
        return remove(size - 1);
    }

    // 从数组中删除元素e
    public void removeElement(E e){
        int index = find(e);
        if(index != -1)
            remove(index);
    }

    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, dataArray.length));
        res.append('[');
        for(int i = 0 ; i < size ; i ++){
            res.append(dataArray[i]);
            if(i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

    // 将数组空间的容量变成newCapacity大小
    private void resize(int newCapacity){

        E[] newData = (E[])new Object[newCapacity];
        for(int i = 0 ; i < size ; i ++) {
            newData[i] = dataArray[i];
        }
        dataArray = newData;
    }



}
