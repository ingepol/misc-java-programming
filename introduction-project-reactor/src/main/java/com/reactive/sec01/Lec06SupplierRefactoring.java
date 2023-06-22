package com.reactive.sec01;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import static com.reactive.utils.FakerUtility.faker;
import static com.reactive.utils.ReactiveUtility.onNext;
import static com.reactive.utils.ReactiveUtility.sleepSeconds;

@Slf4j
public class Lec06SupplierRefactoring {

  public static void main(final String[] args) {
    // It is just building the pipeline, it will not execute the pipeline until subscribe.
    getName();
    getName()
        .subscribeOn(Schedulers.boundedElastic()) // To make this completely asynchronous
        .subscribe(onNext()); // Subscribe execute the pipeline  
    final String name = getName().block(); //blocking consumer, it should not be used in really live
    log.info(name);
    sleepSeconds(4); // To see the printing name, we need sleep the Main thread
  }

  private static Mono<String> getName() {
    log.info("Entered getName method");
    return Mono.fromSupplier(() -> {
      log.info("Generating name...");
      sleepSeconds(3);
      return faker().name().fullName();
    }).map(String::toUpperCase);
  }
}
