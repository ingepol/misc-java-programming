package com.reactive.sec02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Lec02MultipleSubscribers {

  public static void main(final String[] args) {
    final Flux<Integer> flux = Flux.just(1, 2, 3, 4);
    final Flux<Integer> evenFlux = flux.filter(i -> i % 2 == 0);
    flux.subscribe(i -> log.info("Sub 1: {}", i));
    flux.subscribe(i -> log.info("Sub 2: {}", i));
    evenFlux.subscribe(i -> log.info("Sub 3: {}", i));
    log.info("commit");
  }
}
