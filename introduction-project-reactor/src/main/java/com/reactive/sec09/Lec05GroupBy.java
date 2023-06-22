package com.reactive.sec09;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

import static com.reactive.utils.ReactiveUtility.sleepSeconds;

@Slf4j
public class Lec05GroupBy {

  public static void main(final String[] args) {
    Flux.range(1, 30)
        .delayElements(Duration.ofSeconds(1))
        .groupBy(i -> i % 2)
        .subscribe(gf -> process(gf, gf.key()));
    sleepSeconds(60);

  }

  private static void process(final Flux<Integer> flux, final int key) {
    log.info("Called process");
    flux.subscribe(i -> log.info("Key: {}, Item: {}", key, i));
  }


}
