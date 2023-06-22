package com.reactive.sec03;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import static com.reactive.utils.FakerUtility.faker;
import static com.reactive.utils.ReactiveUtility.sleepSeconds;
import static com.reactive.utils.ReactiveUtility.subscriber;

@Slf4j
public class Lec04CreateIssueFix {

  public static void main(final String[] args) {
    //only one instance of fluxSink
    Flux.create(fluxSink -> {
          String country;
          do {
            country = faker().country().name();
            log.info("emitting : {}", country);
            fluxSink.next(country);
          } while (!country.equalsIgnoreCase("canada") && !fluxSink.isCancelled());
          fluxSink.complete();
        })
        // Just cancel the subscription, the fluxSink continue emitting
        // at least ask for fluxSink.isCancelled()
        //.take(3)
        .subscribe(subscriber());
    sleepSeconds(2);
  }
}
