package com.reactive.sec01;

import com.reactive.utils.ReactiveUtility;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscribe {

  public static void main(final String[] args) {
    //Publisher
    final Mono<Integer> mono = Mono
        .just("ball")
        .map(String::length)
        .map(l -> l / 0); // Force raise exception / onError
    // Opc 1.
    //mono.subscribe();
    // Opc 2.
    mono.subscribe(
        ReactiveUtility.onNext(), // Consumer
        ReactiveUtility.onError(), // errorConsumer
        ReactiveUtility.onComplete() // completeConsumer
    );
  }
}
