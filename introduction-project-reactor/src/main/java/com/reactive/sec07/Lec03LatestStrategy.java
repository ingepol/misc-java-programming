package com.reactive.sec07;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import static com.reactive.utils.ReactiveUtility.*;

@Slf4j
public class Lec03LatestStrategy {

  public static void main(final String[] args) {

    // 75% 12
    System.setProperty("reactor.bufferSize.small", "16");

    Flux.create(fluxSink -> {
          for (int i = 0; i < 201; i++) {
            fluxSink.next(i);
            log.info("Pushed {}", i);
            sleepMillis(1);
          }
          fluxSink.complete();
        })
        //Drop the latest element when the buffer is full and next element arrive
        .onBackpressureLatest()
        .publishOn(Schedulers.boundedElastic())
        .doOnNext(i -> sleepMillis(10))
        .subscribe(subscriber());

    sleepSeconds(3);

  }
}
