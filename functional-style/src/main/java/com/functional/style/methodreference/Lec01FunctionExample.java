package com.functional.style.methodreference;

import java.util.function.UnaryOperator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec01FunctionExample {

  static UnaryOperator<String> toUppercase = s -> s.toUpperCase();
  static UnaryOperator<String> toUppercaseMethodReference = String::toUpperCase;

  public static void main(final String[] args) {
    log.info(toUppercase.apply("java8"));
    log.info(toUppercaseMethodReference.apply("java8"));
  }
}
