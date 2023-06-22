package com.reactive.sec06;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import static com.reactive.utils.ReactiveUtility.sleepSeconds;

@Slf4j
public class Lec01ThreadDemo {

  public static void main(final String[] args) {
    final Flux<Object> flux = Flux.create(fluxSink -> {
      printThreadName("create");
      fluxSink.next(1);
    }).doOnNext(i -> printThreadName("next " + i));

    flux.subscribe(c -> printThreadName("current main thread"));

    //if you don't run in main thread, create a runnable
    final Runnable runnable = () -> flux.subscribe(v -> printThreadName("sub " + v));

    for (int i = 0; i < 2; i++) {
      new Thread(runnable).start();
    }

    sleepSeconds(6);

  }

  private static void printThreadName(final String msg) {
    log.info("{} \t\t: Thread: {}", msg, Thread.currentThread().getName());
  }

}
