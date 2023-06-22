package com.reactive.sec02.assignment;

import lombok.Data;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import static com.reactive.utils.FakerUtility.faker;

@Data
public class PriceBubble {

  public final int HIGH = 5;

  public final int LOW = -5;

  private AtomicInteger atomicInteger;

  public PriceBubble(final int num) {
    this.atomicInteger = new AtomicInteger(num);
  }

  public Flux<Integer> getPrice() {
    return Flux.interval(Duration.ofSeconds(1)).
        map(num -> this.atomicInteger.getAndAccumulate(faker().random().nextInt(this.LOW, this.HIGH),
            Integer::sum));
  }

}

