package com.reactive.sec09.assignment;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

import java.time.Duration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderService {

  public static Flux<PurchaseOrder> orderStream() {
    return Flux.interval(Duration.ofMillis(100))
        .map(i -> new PurchaseOrder());
  }
}
