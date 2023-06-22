package com.reactive.sec06;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

import static com.reactive.utils.ReactiveUtility.sleepSeconds;

@Slf4j
public class Lec06Parallel {

  public static void main(final String[] args) {

    final List<Integer> list = new ArrayList<>(); // It is not thread safe, lost items

    Flux.range(1, 1000).parallel()
        .runOn(Schedulers.parallel())
        .subscribe(list::add);

    log.info("{}", list.size());

    Flux.range(1, 10).parallel(2)
        .runOn(Schedulers.boundedElastic())
        .doOnNext(i -> printThreadName("next " + i))
        .subscribe(v -> printThreadName("subs " + v));

    sleepSeconds(5);
  }

  private static void printThreadName(final String msg) {
    log.info("{} \t\t: Thread: {}", msg, Thread.currentThread().getName());
  }
}
