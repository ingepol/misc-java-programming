package com.reactive.sec04;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.reactive.utils.FakerUtility.faker;
import static com.reactive.utils.ReactiveUtility.subscriber;

@Slf4j
public class Lec06OnError {

  public static void main(final String[] args) {
    Flux.range(1, 10)
        //.log()
        .map(i -> 10 / (8 - i))
        //.onErrorReturn(-1)
        //.onErrorResume(e -> fallback())
        .onErrorContinue(
            (err, obj) -> log.info("error: {}, value: {}", err.toString(), obj.toString()))
        .subscribe(subscriber());
  }

  private static Mono<Integer> fallback() {
    return Mono.fromSupplier(() -> faker().random().nextInt(100, 200));
  }
}
