package com.basicstrong.functionalprogramming.section9;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
@SuppressWarnings("unused")
public class InfiniteStream {

  public static void main(String[] args) {
    IntStream.iterate(0, i -> i - 1).limit(15).forEach(val -> log.info(String.valueOf(val)));
    Stream.generate(() -> "Hello").forEach(log::info);
    Stream.generate(new Random()::nextInt).map(String::valueOf).forEach(log::info);
  }
}
