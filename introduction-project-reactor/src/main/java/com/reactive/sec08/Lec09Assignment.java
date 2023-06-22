package com.reactive.sec08;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

import static com.reactive.utils.FakerUtility.faker;
import static com.reactive.utils.ReactiveUtility.sleepSeconds;
import static com.reactive.utils.ReactiveUtility.subscriber;

/**
 * <p>
 * Bought a car for ten thousand dollar at month zero
 * </p><p>
 * The car is depreciating by a hundred dollars every month.
 * </p><p>
 * Demand factor, each quarter generate a random number between 0.8 - 1.2, so that will also affect the car price
 * </p>
 */
@Slf4j
public class Lec09Assignment {

  public static void main(final String[] args) {
    final int carPrice = 10000;
    Flux.combineLatest(monthStream(), demandStream(),
        (month, demand) -> {
          log.info("month {}", month);
          log.info("demand {}", demand);
          return (carPrice - (month * 100)) * demand;
        }
    ).subscribe(subscriber());
    sleepSeconds(20);
  }

  private static Flux<Long> monthStream() {
    return Flux.interval(Duration.ZERO, Duration.ofSeconds(1));
  }

  private static Flux<Double> demandStream() {
    return Flux.interval(Duration.ofSeconds(3))
        .map(i -> faker().random().nextInt(80, 120) / 100d)
        .startWith(1d);
  }
}
