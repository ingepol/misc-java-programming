package com.reactive.sec08;

import reactor.core.publisher.Flux;

import java.time.Duration;

import static com.reactive.utils.ReactiveUtility.sleepSeconds;
import static com.reactive.utils.ReactiveUtility.subscriber;

public class Lec05CombineLatest {

  public static void main(final String[] args) {
    Flux.combineLatest(getString(), getNumber(), (s, i) -> s + i)
        .subscribe(subscriber());
    sleepSeconds(10);
  }

  private static Flux<String> getString() {
    return Flux.just("A", "B", "C", "D")
        .delayElements(Duration.ofSeconds(1));
  }

  private static Flux<Integer> getNumber() {
    return Flux.just(1, 2, 3)
        .delayElements(Duration.ofSeconds(3));
  }
}
