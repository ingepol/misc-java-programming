package com.reactive.sec01;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

import static com.reactive.utils.FakerUtility.faker;
import static com.reactive.utils.ReactiveUtility.onNext;

@Slf4j
public class Lec05MonoFromSupplier {

  public static void main(final String[] args) {
    //use  just only when you have data already
    //Mono<String> mono = Mono.just(getName());

    final Supplier<String> stringSupplier = Lec05MonoFromSupplier::getName;
    final Mono<String> mono = Mono.fromSupplier(stringSupplier);
    mono.subscribe(onNext());

    final Callable<String> stringCallable = Lec05MonoFromSupplier::getName;
    Mono.fromCallable(stringCallable).subscribe(onNext());
  }

  private static String getName() {
    log.info("Generating name...");
    return faker().name().fullName();
  }
}
