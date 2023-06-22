package com.basicstrong.functionalprogramming.section9;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Stream;

@Slf4j
public class ReduceOperation {

  public static void main(String[] args) {

    Integer sum = Stream.of(1, 2, 34, 56, 76, 87, 89, 90)
        .reduce(Integer::sum).orElse(0);
    log.info(String.valueOf(sum));

    Integer multiply = Stream.of(1, 2, 34, 56, 76, 87, 89, 90)
        .reduce((a, b) -> a * b).orElse(0);
    log.info(String.valueOf(multiply));
  }
}
