package com.functional.style.streams.numerics;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec01IntExample {

  public static int sumOfNumbers(final List<Integer> integerList) {
    return integerList.stream()
        .reduce(0, Integer::sum);
  }

  public static int sumOfNumbersIntStream() {
    return IntStream.rangeClosed(1, 6)
        .sum();
  }

  public static void main(final String[] args) {
    log.info("Sum of N Numbers: {}", sumOfNumbers(Arrays.asList(1, 2, 4, 5, 6)));
    log.info("Sum of N Numbers using IntStream: {}", sumOfNumbersIntStream());
  }
}
