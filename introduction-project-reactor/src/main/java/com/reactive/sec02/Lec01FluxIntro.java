package com.reactive.sec02;

import reactor.core.publisher.Flux;

import static com.reactive.utils.FakerUtility.faker;
import static com.reactive.utils.ReactiveUtility.*;

public class Lec01FluxIntro {

  public static void main(final String[] args) {
    final Flux<Object> flux = Flux.just(1, 2, 3, 4, "1", faker().name().fullName());
    flux.subscribe(
        onNext(),
        onError(),
        onComplete()
    );
  }
}
