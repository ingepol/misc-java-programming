package com.reactive.sec06;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

import static com.reactive.utils.ReactiveUtility.sleepSeconds;
import static com.reactive.utils.ReactiveUtility.subscriber;

public class Lec07FluxInterval {

  public static void main(final String[] args) {
    Flux.interval(Duration.ofSeconds(1)) //Internal use Scheduler parallel
        .subscribeOn(Schedulers.boundedElastic())
        .subscribe(subscriber());

    sleepSeconds(2);
  }
}
