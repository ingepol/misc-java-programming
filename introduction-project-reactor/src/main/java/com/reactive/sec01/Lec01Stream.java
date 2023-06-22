package com.reactive.sec01;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Stream;

@Slf4j
public class Lec01Stream {

  public static void main(String[] args) {
    Stream<Integer> stream = Stream.of(1)
        .map(i -> {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            log.error("It was an error");
            Thread.currentThread().interrupt();
          }
          return i * 2;
        });
    log.info(stream.toString());
    stream.forEach(i -> log.info("Result: {}", i));
  }
}
