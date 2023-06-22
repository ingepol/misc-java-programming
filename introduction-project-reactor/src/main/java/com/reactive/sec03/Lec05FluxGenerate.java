package com.reactive.sec03;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import static com.reactive.utils.FakerUtility.faker;
import static com.reactive.utils.ReactiveUtility.subscriber;

@Slf4j
public class Lec05FluxGenerate {

  public static void main(final String[] args) {
    //Run like a loop, infinitive
    //synchronousSink just emit one item
    Flux.generate(synchronousSink -> {
          log.info("emitting");
          synchronousSink.next(faker().country().name());
          // Force complete after emit the first element otherwise runs again and again
          synchronousSink.complete();
        })
        //.take(2) // Cancels subscription
        .subscribe(subscriber());
  }
}
