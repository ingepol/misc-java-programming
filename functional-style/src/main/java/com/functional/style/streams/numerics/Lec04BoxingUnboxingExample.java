package com.functional.style.streams.numerics;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec04BoxingUnboxingExample {

  public static void main(final String[] args) {
    log.info("Boxing {}", boxing().toString());
    log.info("Unboxing {}", unBoxing(Arrays.asList(1, 2, 6, 7)));
  }

  private static List<Integer> boxing() {
    return IntStream.rangeClosed(1, 10)
        .boxed()
        .collect(Collectors.toList());
  }

  private static int unBoxing(final List<Integer> integerList) {
    return integerList.stream()
        .mapToInt(Integer::intValue)
        .sum();
  }
}
