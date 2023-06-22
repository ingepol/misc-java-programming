package com.reactive.sec06;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import static com.reactive.utils.ReactiveUtility.sleepSeconds;

@Slf4j
public class Lec04PublishOn {

  public static void main(final String[] args) {
    final Flux<Object> flux = Flux.create(fluxSink -> {
          printThreadName("create");
          for (int i = 0; i < 3; i++) {
            fluxSink.next(i + 1);
            sleepSeconds(1);
          }
          fluxSink.complete();
        })
        .doOnNext(i -> printThreadName("next " + i));

    flux
        .publishOn(Schedulers.boundedElastic())
        .doOnNext(i -> printThreadName("next " + i))
        .publishOn(Schedulers.parallel())
        .subscribe(c -> printThreadName("Subs " + c));

    sleepSeconds(5);

  }

  private static void printThreadName(final String msg) {
    log.info("{} \t\t: Thread: {}", msg, Thread.currentThread().getName());
  }
}
