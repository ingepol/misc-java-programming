package com.functional.style.functionainterfaces;

import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec07FunctionExample {

  static final Function<String, String> function = String::toUpperCase;
  static final Function<String, String> addSomeString = name -> name.toUpperCase()
      .concat("default");

  public static void main(final String[] args) {
    log.info("Result of andThen is: {}", function.andThen(addSomeString).apply("Java8"));
    log.info("Result of compose is: {}", function.compose(addSomeString).apply("Java8"));
  }
}
