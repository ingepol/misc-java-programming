package com.reactive.sec02;

import reactor.core.publisher.Flux;

import java.time.Duration;

import static com.reactive.utils.ReactiveUtility.onNext;
import static com.reactive.utils.ReactiveUtility.sleepSeconds;

public class Lec08FluxInterval {

  public static void main(final String[] args) {
    Flux.interval(Duration.ofSeconds(1))
        .subscribe(onNext());
    //It is not blocking, so we need sleep the main Thread to see the result.
    sleepSeconds(5);
  }
}
