package com.reactive.sec10;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import static com.reactive.utils.FakerUtility.faker;
import static com.reactive.utils.ReactiveUtility.subscriber;

@Slf4j
public class Lec02Retry {

  public static void main(final String[] args) {
    getIntegers()
        .retry(2) //works for error signal
        .subscribe(subscriber());
  }

  private static Flux<Integer> getIntegers() {
    return Flux.range(1, 3).
        doOnSubscribe(s -> log.info("Subscribed doOnSubscribe"))
        .doOnComplete(() -> log.info("-- Completed"))
        .map(i -> i / (faker().random().nextInt(1, 5) > 3 ? 0 : 1))
        .doOnError(err -> log.error("--error"));
  }
}
