package com.reactive.sec02;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.reactive.utils.ReactiveUtility.*;

public class Lec09FluxFromMono {

  public static void main(final String[] args) {
    final Mono<String> mono = Mono.just("a");
    final Flux<String> flux = Flux.from(mono);
    flux.subscribe(onNext());

    //FluxToMono
    final Mono<Integer> next = Flux.range(1, 10)
        .filter(i -> i > 3)
        .next();// Return the first item from flux, into a mono.
    next.subscribe(onNext(), onError(), onComplete());
  }
}
