package com.functional.style.imperativevsdeclarative;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

@Slf4j
public class Lec01Example {

  public static void main(final String[] args) {

    // Imperative - how style of programming

    int sum = 0;
    for (int i = 0; i < 100; i++) {
      sum += i;
    }
    log.info("Sum using Imperative Approach: {}", sum);

    // Declarative Style of programming - What style of programming

    log.info("Sum using Imperative Approach: {}", IntStream.rangeClosed(1, 100).sum());
  }
}
