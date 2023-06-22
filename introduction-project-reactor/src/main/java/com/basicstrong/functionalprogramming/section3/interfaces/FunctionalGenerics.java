package com.basicstrong.functionalprogramming.section3.interfaces;

@FunctionalInterface
public interface FunctionalGenerics<T, R> {

  R execute(T t);

}
