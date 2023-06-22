package com.basicstrong.functionalprogramming.section6;

@SuppressWarnings("unused")
public class ImpureFunction {

  private int value = 0;

  @SuppressWarnings("unused")
  public int add(int nextValue) {
    this.value += nextValue;
    return this.value;
  }

}
