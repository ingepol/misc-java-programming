package com.basicstrong.functionalprogramming.section6;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReferentialTransparency {

  public static void main(String[] args) {
    //Referential transparency is A property of a function, variable, 
    // or expression whereby the expression can be replaced by its (evaluated) 
    // value without affecting the behaviour of the program.
    int result = add(2, multiply(2, multiply(2, 2)));
    log.info(String.valueOf(result));
    result = add(2, multiply(2, 4));
    log.info(String.valueOf(result));
    result = add(2, multiply(2, 4));
    log.info(String.valueOf(result));
    result = add(2, 8);
    log.info(String.valueOf(result));
  }

  public static int add(int a, int b) {
    return a + b;
  }

  public static int multiply(int a, int b) {
    log.info(String.format("Multiplying %d and %d -> %d", a, b, a * b));
    return a * b;
  }
}
