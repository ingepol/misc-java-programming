package com.basicstrong.functionalprogramming.section6.techniques;

import com.basicstrong.functionalprogramming.section6.interfaces.Function;
import lombok.extern.slf4j.Slf4j;

import java.util.function.IntUnaryOperator;

@Slf4j
public class Currying {

  public static void main(String[] args) {
    // Currying: is a technique which basically restructures a multi-parameters function
    // into multiple functions having single parameter each.
    Function<Integer, Function<Integer, IntUnaryOperator>> fn1 = u -> v -> w -> u + v + w;
    Function<Integer, IntUnaryOperator> fn2 = fn1.apply(1);
    IntUnaryOperator fn3 = fn2.apply(3);
    Integer sum = fn3.applyAsInt(2);
    log.info(String.valueOf(sum));
  }
}
