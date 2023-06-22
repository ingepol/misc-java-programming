package com.reactive.sec04;

import reactor.core.publisher.Flux;

import java.time.Duration;

import static com.reactive.utils.ReactiveUtility.sleepSeconds;
import static com.reactive.utils.ReactiveUtility.subscriber;
import static java.lang.Thread.sleep;

public class Lec07TimeOut {

  public static void main(final String[] args) {
    getOrderNumbers().timeout(Duration.ofSeconds(2), fallback())
        .subscribe(subscriber());
    sleepSeconds(60);
  }

  private static Flux<Integer> getOrderNumbers() {
    try {
      sleep(5000);
    } catch (final InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    return Flux.range(1, 10).delayElements(Duration.ofSeconds(5));
  }

  private static Flux<Integer> fallback() {
    return Flux.range(100, 10).delayElements(Duration.ofMillis(200));
  }
}
