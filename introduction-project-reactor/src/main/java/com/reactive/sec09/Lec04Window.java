package com.reactive.sec09;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import static com.reactive.utils.ReactiveUtility.sleepSeconds;
import static com.reactive.utils.ReactiveUtility.subscriber;

@Slf4j
public class Lec04Window {

  private static final AtomicInteger atomicInteger = new AtomicInteger(1);

  public static void main(final String[] args) {
    eventStream()
        .window(5)
        .flatMap(Lec04Window::saveEvents)
        .subscribe(subscriber());
    sleepSeconds(30);
  }

  private static Flux<String> eventStream() {
    return Flux.interval(Duration.ofMillis(500))
        .map(i -> "event" + i);
  }

  private static Mono<Integer> saveEvents(final Flux<String> flux) {
    return flux.doOnNext(e -> log.info("saving " + e))
        .doOnComplete(() -> {
          log.info("Saving this batch");
          log.info("----------------");
        })
        .then(Mono.just(atomicInteger.getAndIncrement()));
  }

}
