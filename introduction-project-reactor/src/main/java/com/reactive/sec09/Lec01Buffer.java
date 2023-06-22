package com.reactive.sec09;

import reactor.core.publisher.Flux;

import java.time.Duration;

import static com.reactive.utils.ReactiveUtility.sleepSeconds;
import static com.reactive.utils.ReactiveUtility.subscriber;

public class Lec01Buffer {

  public static void main(final String[] args) {
    eventStream()
        //.buffer(5)
        //.buffer(Duration.ofSeconds(2))
        .bufferTimeout(5, Duration.ofSeconds(2))
        .subscribe(subscriber());
    sleepSeconds(30);
  }

  private static Flux<String> eventStream() {
    return Flux.interval(Duration.ofMillis(800))
        .map(i -> "event" + i);
  }

}
