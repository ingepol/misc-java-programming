package com.reactive.sec05.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;

import static java.util.Objects.isNull;

public class OrderService {

  private Flux<PurchaseOrder> flux;

  public Flux<PurchaseOrder> orderStream() {
    if (isNull(this.flux)) {
      this.flux = this.getOrderStream();
    }
    return this.flux;
  }


  private Flux<PurchaseOrder> getOrderStream() {
    return Flux.interval(Duration.ofMillis(100))
        .map(i -> new PurchaseOrder())
        .publish()
        .refCount(2);
  }
}
