package com.reactive.sec07;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import static com.reactive.utils.ReactiveUtility.*;

@Slf4j
public class Lec01Demo {

  public static void main(final String[] args) {
    Flux.create(fluxSink -> {
          for (int i = 0; i < 501; i++) {
            fluxSink.next(i);
            log.info("Pushed {}", i);
          }
          fluxSink.complete();
        }).publishOn(Schedulers.boundedElastic())
        .doOnNext(i -> sleepMillis(10))
        .subscribe(subscriber());

    sleepSeconds(60);
  }
}
