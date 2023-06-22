package com.functional.style.streams.numerics;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec02RangesExample {

  public static void main(final String[] args) {
    final IntStream range = IntStream.range(1, 50);
    log.info("Range Count {}", range.count());

    final IntStream rangeClosed = IntStream.rangeClosed(1, 50);
    log.info("Range Closed Count {}", rangeClosed.count());

    log.info("Long Stream Range Closed Count {}", LongStream.rangeClosed(1,50).count());
    
    IntStream.range(1, 5).asDoubleStream().forEach(value -> log.info("{}", value));

  }
}
