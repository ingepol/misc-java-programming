package com.reactive.sec04;

import reactor.core.publisher.Flux;

import static com.reactive.utils.ReactiveUtility.subscriber;

public class Lec09SwitchIfEmpty {

  public static void main(final String[] args) {
    getOrderNumbers()
        .filter(i -> i > 10)
        .switchIfEmpty(fallback())
        .subscribe(subscriber());
  }

  // Redis Cache
  private static Flux<Integer> getOrderNumbers() {
    return Flux.range(1, 10);
  }

  //db
  private static Flux<Integer> fallback() {
    return Flux.range(20, 5);
  }

}
