package com.basicstrong.functionalprogramming.section4;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.function.BinaryOperator;

@Slf4j
public class StaticReference {

  public static void main(String[] args) {
    BinaryOperator<String> operator = A_Class::staticMethod;
    log.info(operator.apply("Basics", "Strong"));
  }
}

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class A_Class {

  static String staticMethod(String a, String b) {
    return a + b;
  }
}
