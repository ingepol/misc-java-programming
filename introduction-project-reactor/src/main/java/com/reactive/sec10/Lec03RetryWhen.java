package com.reactive.sec10;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;

import static com.reactive.utils.FakerUtility.faker;
import static com.reactive.utils.ReactiveUtility.sleepSeconds;
import static com.reactive.utils.ReactiveUtility.subscriber;

@Slf4j
public class Lec03RetryWhen {

  public static void main(final String[] args) {
    getIntegers()
        .retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(3))) //works for error signal
        .subscribe(subscriber());
    sleepSeconds(20);
  }

  private static Flux<Integer> getIntegers() {
    return Flux.range(1, 3).
        doOnSubscribe(s -> log.info("Subscribed doOnSubscribe"))
        .doOnComplete(() -> log.info("-- Completed"))
        .map(i -> i / (faker().random().nextInt(1, 5) > 3 ? 0 : 1))
        .doOnError(err -> log.error("--error"));
  }
}
