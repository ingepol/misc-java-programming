package com.reactive.sec06;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import static com.reactive.utils.ReactiveUtility.sleepSeconds;

@Slf4j
public class Lec02SubscribeOnDemo {

  public static void main(final String[] args) {
    final Flux<Object> flux = Flux.create(fluxSink -> {
          printThreadName("create");
          fluxSink.next(1);
        })
        .subscribeOn(Schedulers.newParallel("vins"))
        .doOnNext(i -> printThreadName("next " + i));

    final Runnable runnable = () -> flux
        .doFirst(() -> printThreadName("First 2"))
        .subscribeOn(Schedulers.boundedElastic())
        .doFirst(() -> printThreadName("First 1"))
        .subscribe(c -> printThreadName("Subscribe"));

    for (int i = 0; i < 2; i++) {
      new Thread(runnable).start();
    }

    sleepSeconds(3);

  }

  private static void printThreadName(final String msg) {
    log.info("{} \t\t: Thread: {}", msg, Thread.currentThread().getName());
  }
}
