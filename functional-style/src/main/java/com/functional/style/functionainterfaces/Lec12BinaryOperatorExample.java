package com.functional.style.functionainterfaces;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec12BinaryOperatorExample {

  static Comparator<Integer> comparator = (a, b) -> a.compareTo(b);

  public static void main(final String[] args) {
    final BinaryOperator<Integer> binaryOperator = (a, b) -> a * b;
    log.info("Result: {}", binaryOperator.apply(3, 4));

    final BinaryOperator<Integer> maxBy = BinaryOperator.maxBy(comparator);
    log.info("result of maxBy is: {}", maxBy.apply(4, 5));

    final BinaryOperator<Integer> minBy = BinaryOperator.minBy(comparator);
    log.info("result of minBy is: {}", minBy.apply(4, 5));

  }
}
