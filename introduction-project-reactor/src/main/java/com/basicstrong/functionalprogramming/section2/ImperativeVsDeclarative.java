package com.basicstrong.functionalprogramming.section2;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

@Slf4j
public class ImperativeVsDeclarative {

  public static void main(String[] args) {
    // Imperative
    int sumOfEvent = 0;
    for (int i = 0; i <= 100; i++) {
      if (i % 2 == 0) {
        sumOfEvent = sumOfEvent + i;
      }
    }
    log.info("sumOfEvent imperative way: " + sumOfEvent);

    // Declarative or Functional
    sumOfEvent = IntStream.rangeClosed(0, 100)
        .filter(num -> num % 2 == 0)
        .reduce(Integer::sum)
        .orElse(0);

    log.info("sumOfEvent functional way: " + sumOfEvent);
  }
}
