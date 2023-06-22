package com.reactive.sec10;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

import static com.reactive.utils.FakerUtility.faker;
import static com.reactive.utils.ReactiveUtility.sleepSeconds;
import static com.reactive.utils.ReactiveUtility.subscriber;

@Slf4j
public class Lec04RetryWhenAdvance {

  public static void main(final String[] args) {
    orderService(faker().business().creditCardNumber())
        .doOnError(err -> log.error(err.getMessage()))
        .retryWhen(Retry.from(
            flux -> flux.doOnNext(rs -> {
                  log.info(String.format("%d", rs.totalRetries()));
                  log.info(rs.failure().getMessage());
                })
                .handle((retrySignal, synchronousSink) -> {
                  if (retrySignal.failure().getMessage().equals("500")) {
                    synchronousSink.next(1);
                  } else {
                    synchronousSink.error(retrySignal.failure());
                  }
                })
                .delayElements(Duration.ofSeconds(1))
        ))
        .subscribe(subscriber());

    sleepSeconds(60);
  }

  // Order Service
  private static Mono<String> orderService(final String ccNumber) {
    return Mono.fromSupplier(() -> {
      processPayment(ccNumber);
      return faker().idNumber().valid();
    });
  }

  //Payment Service
  private static void processPayment(final String ccNumber) {
    final int random = faker().random().nextInt(1, 10);
    if (random < 8) {
      throw new RuntimeException("500");
    } else if (random < 10) {
      throw new RuntimeException("404");
    }
  }
}
