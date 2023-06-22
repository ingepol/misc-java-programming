package com.reactive.sec10;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

import static com.reactive.utils.ReactiveUtility.subscriber;

@Slf4j
public class Lec01Repeat {

  private static final AtomicInteger atomicInteger = new AtomicInteger(1);

  public static void main(final String[] args) {
    getIntegers()
        //.repeat(2) //works for complete signal
        //.repeat() // infinitive
        .repeat(() -> atomicInteger.getAndIncrement() < 14)
        .subscribe(subscriber());
  }

  private static Flux<Integer> getIntegers() {
    return Flux.range(1, 3).
        doOnSubscribe(s -> log.info("Subscribed doOnSubscribe"))
        .doOnComplete(() -> log.info("-- Completed"))
        .map(i -> atomicInteger.getAndIncrement());
  }
}
