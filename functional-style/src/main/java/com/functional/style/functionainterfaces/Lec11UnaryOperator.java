package com.functional.style.functionainterfaces;

import java.util.function.UnaryOperator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec11UnaryOperator {

  static UnaryOperator<String> unaryOperator = s -> s.concat("Default");

  public static void main(final String[] args) {
    log.info(unaryOperator.apply("java8"));
  }
}
