package com.basicstrong.functionalprogramming.section9;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Stream;

@Slf4j
public class FilterOperation {

  public static void main(String[] args) {
    Stream.of(36, 678, 97, 89, 53, 32, 325, 6)
        .filter(e -> e % 2 == 0)
        .forEach(e -> log.info(String.valueOf(e)));
  }
}
