package com.reactive.sec03;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import static com.reactive.utils.FakerUtility.faker;
import static com.reactive.utils.ReactiveUtility.subscriber;

@Slf4j
public class Lec06FluxGenerateAssignment {

  public static void main(final String[] args) {

    // canada
    // max = 10
    // subscriber cancels - exit
    Flux.generate(synchronousSink -> {
      final String country = faker().country().name();
      log.info("emitting {}", country);
      synchronousSink.next(country);
      if (country.equalsIgnoreCase("canada")) {
        synchronousSink.complete();
      }
    }).subscribe(subscriber());
  }

}
