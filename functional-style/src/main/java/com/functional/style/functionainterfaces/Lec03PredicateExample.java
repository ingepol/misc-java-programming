package com.functional.style.functionainterfaces;

import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec03PredicateExample {

  static final Predicate<Integer> numEven = num -> num % 2 == 0;

  static final Predicate<Integer> numDivByFive = num -> num % 5 == 0;

  public static void main(final String[] args) {

    log.info(String.valueOf(numEven.test(4)));
    predicateAnd();
    predicateOr();
    predicateNegative();
  }

  private static void predicateAnd() {
    log.info("predicateAnd");
    log.info(String.valueOf(numEven.and(numDivByFive).test(10)));
    log.info(String.valueOf(numEven.and(numDivByFive).test(8)));
  }

  private static void predicateOr() {
    log.info("predicateOr");
    log.info(String.valueOf(numEven.or(numDivByFive).test(10)));
    log.info(String.valueOf(numEven.or(numDivByFive).test(8)));
  }

  private static void predicateNegative() {
    log.info("predicateNegative");
    log.info(String.valueOf(numEven.or(numDivByFive).negate().test(8)));
  }
}
