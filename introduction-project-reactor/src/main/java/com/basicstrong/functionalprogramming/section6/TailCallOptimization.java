package com.basicstrong.functionalprogramming.section6;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TailCallOptimization {

  public static void main(String[] args) {

  }

  public static long reFact(int n) {
    if (n <= 1) {
      return 1;
    }
    return n * reFact(n - 1);
  }

  public static long tailRefact(int n, int a) {
    if (n <= 1) {
      return a;
    }
    return tailRefact(n - 1, n * a);
  }

}
