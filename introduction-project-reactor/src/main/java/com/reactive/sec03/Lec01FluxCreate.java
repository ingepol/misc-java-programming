package com.reactive.sec03;

import reactor.core.publisher.Flux;

import static com.reactive.utils.FakerUtility.faker;
import static com.reactive.utils.ReactiveUtility.sleepSeconds;
import static com.reactive.utils.ReactiveUtility.subscriber;

public class Lec01FluxCreate {

  public static void main(final String[] args) {
    Flux.create(fluxSink -> {
      String country;
      do {
        country = faker().country().name();
        fluxSink.next(country);
      } while (!country.equalsIgnoreCase("canada"));
      fluxSink.complete();
    }).subscribe(subscriber());
    sleepSeconds(2);
  }
}
