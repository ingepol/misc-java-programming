package com.basicstrong.functionalprogramming.section4;

import lombok.extern.slf4j.Slf4j;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

@Slf4j
public class BiFunctionPractice {

  public static void main(String[] args) {
    //Use BinaryOperator instead BiFunction when the three arguments are the same
    BiFunction<String, String, String> biFunction = (a, b) -> a + b;
    BiFunction<String, String, Integer> biFunctionLen = (a, b) -> (a + b).length();
    log.info(biFunction.apply("Basics", "Strong"));
    log.info(String.valueOf(biFunctionLen.apply("Basics", "Strong")));

    // BinaryOperator specialised function
    BinaryOperator<String> operator = (a, b) -> a + "." + b;
    log.info(String.valueOf(operator.apply("BasicsStrong", "com")));
  }
}
