package com.reactive.sec04;

import reactor.core.publisher.Flux;

import static com.reactive.utils.ReactiveUtility.subscriber;

public class Lec08DefaultEmpty {

  public static void main(final String[] args) {
    getOrderNumbers()
        .filter(i -> i > 10)
        .defaultIfEmpty(-100)
        .subscribe(subscriber());
  }

  private static Flux<Integer> getOrderNumbers() {
    return Flux.range(1, 12);
  }
}
