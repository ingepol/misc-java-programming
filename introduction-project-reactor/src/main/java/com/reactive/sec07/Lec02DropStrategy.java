package com.reactive.sec07;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

import static com.reactive.utils.ReactiveUtility.*;

@Slf4j
public class Lec02DropStrategy {

  public static void main(final String[] args) {

    // 75% 12
    System.setProperty("reactor.bufferSize.small", "16");

    final List<Object> list = new ArrayList<>();

    Flux.create(fluxSink -> {
          for (int i = 0; i < 501; i++) {
            fluxSink.next(i);
            log.info("Pushed {}", i);
            sleepMillis(1);
          }
          fluxSink.complete();
        }).onBackpressureDrop(list::add) //Drop all elements when the buffer is full
        .publishOn(Schedulers.boundedElastic())
        .doOnNext(i -> sleepMillis(10))
        .subscribe(subscriber());

    sleepSeconds(10);
    log.info(list.toString());
  }
}
