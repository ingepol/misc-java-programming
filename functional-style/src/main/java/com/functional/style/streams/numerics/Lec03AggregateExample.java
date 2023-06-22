package com.functional.style.streams.numerics;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec03AggregateExample {

  public static void main(final String[] args) {
    final int sum = IntStream.rangeClosed(1, 50).sum();
    log.info("Sum is: {}", sum);

    final OptionalInt optionalInt = IntStream.rangeClosed(1, 50).max();

    log.info("Count {}", IntStream.rangeClosed(0, 0).count());
    log.info("Max {}", optionalInt.isPresent() ? optionalInt.getAsInt() : 0);

    final OptionalLong optionalLong = LongStream.rangeClosed(50, 100).min();
    log.info("Min {}", optionalLong.isPresent() ? optionalLong.getAsLong() : 0);

    final OptionalDouble average = IntStream.rangeClosed(50, 100).average();
    log.info("Average {}", average.isPresent() ? average.getAsDouble() : 0);

  }
}
