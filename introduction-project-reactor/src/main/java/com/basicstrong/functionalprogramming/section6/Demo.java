package com.basicstrong.functionalprogramming.section6;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo {

  public static void main(String[] args) {
    int add = add(multiply(2, 3), multiply(3, 4));
    log.info(String.valueOf(add));

    int result = add(6, 12);
    log.info(String.valueOf(result));
  }

  public static int add(int a, int b) {
    return a + b;
  }

  public static int multiply(int a, int b) {
    log(String.format("Returning %s as the result of %s * %s", a * b, a, b));
    return a * b;
  }

  public static void log(String m) {
    log.info(m);
  }
}
