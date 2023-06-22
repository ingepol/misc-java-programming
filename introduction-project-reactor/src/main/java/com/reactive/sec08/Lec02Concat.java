package com.reactive.sec08;

import reactor.core.publisher.Flux;

import static com.reactive.utils.ReactiveUtility.subscriber;

public class Lec02Concat {

  public static void main(final String[] args) {
    final Flux<String> flux1 = Flux.just("a", "b");
    final Flux<String> flux2 = Flux.error(new RuntimeException("oops!"));
    final Flux<String> flux3 = Flux.just("c", "d", "e");

    final Flux<String> flux = Flux.concatDelayError(flux1, flux2, flux3);

    flux.subscribe(subscriber());
  }
}
