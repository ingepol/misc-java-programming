package com.basicstrong.functionalprogramming.section9;

import lombok.extern.slf4j.Slf4j;

import java.util.IntSummaryStatistics;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@Slf4j
public class Calculations {

  public static void main(String[] args) {
    //Sum
    int sum = IntStream.of(1, 2, 3).sum();
    log.info(String.valueOf(sum));

    OptionalInt max = IntStream.of(1, 2, 3).max();
    log.info(String.valueOf(max.getAsInt())); //If the stream is empty, an exception will be thrown.

    OptionalInt min = IntStream.of(1, 2, 3).min();
    log.info(String.valueOf(min.getAsInt()));

    double v = IntStream.of(1, 2, 3).average().orElse(0);
    log.info(String.valueOf(v));

    IntSummaryStatistics statistics = IntStream.of(1, 2, 3).summaryStatistics();
    log.info(statistics.toString());
  }


}
