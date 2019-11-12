package com.yswg.mycodedemo1.tlambda;

@FunctionalInterface
public interface MyFun<T> {
    T getValue(T t);
}
