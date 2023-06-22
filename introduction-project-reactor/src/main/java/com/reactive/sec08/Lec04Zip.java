package com.reactive.sec08;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import static com.reactive.utils.ReactiveUtility.subscriber;

@Slf4j
public class Lec04Zip {

  public static void main(final String[] args) {
    Flux.zip(getBody(), getEngine(), getTires())
        .doOnNext(tuple3 -> log.info(tuple3.getT2()))
        .subscribe(subscriber());
  }

  private static Flux<String> getBody() {
    return Flux.range(1, 5)
        .map(i -> "body");
  }

  private static Flux<String> getEngine() {
    return Flux.range(1, 2)
        .map(i -> "engine");
  }

  private static Flux<String> getTires() {
    return Flux.range(1, 6)
        .map(i -> "tires");
  }

}
