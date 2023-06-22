package com.functional.style.lambdas;

import java.util.function.IntConsumer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec03Variable {

  static int valueField; // instance local

  public static void main(final String[] args) {
    final int i = 0;
    final int value = 4; // local variable
    // inside lambda can't use local variable, as declaration
    final IntConsumer c1 = i1 -> log.info("Value is " + i);
    c1.accept(i);

    final IntConsumer c2 = i2 -> {
      // value++;  inside lambda cant be modify local variable
      valueField++;
      log.info("Result local variable is: {}", value + i2);
      log.info("Result class variable is: {}", valueField + i2);
    };
    c2.accept(4);
  }
}
