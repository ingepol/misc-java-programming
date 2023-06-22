package com.reactive.sec04;

import reactor.core.publisher.Flux;

import java.time.Duration;

import static com.reactive.utils.ReactiveUtility.sleepSeconds;
import static com.reactive.utils.ReactiveUtility.subscriber;

public class Lec05Delay {

  public static void main(final String[] args) {
    Flux.range(1, 100)
        .log()
        .delayElements(Duration.ofSeconds(1))
        .subscribe(subscriber());
    sleepSeconds(60);
  }
}
