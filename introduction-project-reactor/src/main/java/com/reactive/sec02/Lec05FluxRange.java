package com.reactive.sec02;

import reactor.core.publisher.Flux;

import static com.reactive.utils.FakerUtility.faker;
import static com.reactive.utils.ReactiveUtility.onNext;

public class Lec05FluxRange {

  public static void main(final String[] args) {
    Flux.range(1, 10)
        .log()
        .map(i -> i + " " + faker().name().fullName())
        .log()
        .subscribe(onNext());
  }
}
