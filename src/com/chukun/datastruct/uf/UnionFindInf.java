package com.chukun.datastruct.uf;

/**
 * 并查集的接口定义
 */
public interface UnionFindInf {

    int getSize();
    boolean isConnected(int p, int q);
    void unionElements(int p, int q);
}
