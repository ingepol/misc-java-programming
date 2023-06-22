package com.functional.style.streams.numerics;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec05MapExample {

  private static List<Double> mapToObj() {
    return IntStream.rangeClosed(1, 5)
        .mapToObj(Double::new).collect(Collectors.toList());
  }

  private static long mapToLong() {
    return IntStream.rangeClosed(1, 5)
        .mapToLong(i -> i)
        .sum();
  }

  private static double mapToDouble() {
    return IntStream.rangeClosed(1, 5)
        .mapToDouble(i -> i)
        .sum();
  }

  public static void main(final String[] args) {
    log.info("MapToObj: {}", mapToObj());
    log.info("MapToLong: {}", mapToLong());
    log.info("MapToDouble: {}", mapToDouble());
  }
}
