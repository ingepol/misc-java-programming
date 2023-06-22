package com.functional.style.functionainterfaces;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec08FunctionReuseExample {

  public static String performConcat(final String str) {
    return Lec07FunctionExample.addSomeString.apply(str);
  }

  public static void main(final String[] args) {
    final String result = performConcat("Hello");
    log.info("Result: {}", result);
  }
}
