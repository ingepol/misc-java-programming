package com.reactive.sec03;

import reactor.core.publisher.Flux;

import static com.reactive.utils.FakerUtility.faker;
import static com.reactive.utils.ReactiveUtility.subscriber;

public class Lec07FluxGenerateCounter {

  public static void main(final String[] args) {
    Flux.generate(
        () -> 1,
        (counter, sink) -> {
          final String country = faker().country().name();
          sink.next(country);
          if (counter >= 10 || country.equalsIgnoreCase("canada")) {
            sink.complete();
          }
          return counter + 1;
        }
    ).subscribe(subscriber());
  }
}
