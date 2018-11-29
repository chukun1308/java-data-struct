package com.chukun.datastruct.inf;

/**
 * 用户自定义对线段树的操作
 * @param <E>
 */
public interface MergeOperator<E> {

    E merge(E a,E b);
}
