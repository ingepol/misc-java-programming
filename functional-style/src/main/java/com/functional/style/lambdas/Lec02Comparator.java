package com.functional.style.lambdas;

import java.util.Comparator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec02Comparator {

  public static void main(final String[] args) {
    // prior java 8
    final Comparator<Integer> comparator = new Comparator<Integer>() {
      @Override
      public int compare(final Integer integer, final Integer t1) {
        return integer.compareTo(t1);
      }
    };
    log.info("Result of the comparator is: {}", comparator.compare(3, 2));

    // Java 8
    final Comparator<Integer> comparatorLambda = (Integer a, Integer b) -> a.compareTo(b);
    log.info("Result of the comparator using lambda is: {}", comparatorLambda.compare(3, 2));

    final Comparator<Integer> comparatorLambda2 = (a, b) -> a.compareTo(b);
    log.info("Result of the comparator using lambda2 is: {}", comparatorLambda2.compare(3, 2));

    final Comparator<Integer> comparatorLambda3 = Integer::compareTo;
    log.info("Result of the comparator using lambda3 is: {}", comparatorLambda3.compare(3, 2));
  }
}
