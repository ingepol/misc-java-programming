package com.reactive.sec04;

import reactor.core.publisher.Flux;

import static com.reactive.utils.ReactiveUtility.subscriber;

public class Lec04LimitRate {

  public static void main(final String[] args) {
    Flux.range(1, 1000)
        .log()
        .limitRate(100, 99)
        .subscribe(subscriber());
  }
}
