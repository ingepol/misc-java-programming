package com.basicstrong.functionalprogramming.section2;

import com.basicstrong.functionalprogramming.section2.interfaces.MathOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LambdaPractice2 {

  public static void main(String[] args) {
    MathOperation add = (a, b) -> log.info(String.valueOf(a + b));
    add.operation(10, 20);

    MathOperation multiply = (a, b) -> log.info(String.valueOf(a * b));
    multiply.operation(10, 90);
  }

}
