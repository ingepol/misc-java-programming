package com.reactive.sec09.assignment;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderProcessor {

  public static Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>> automotiveProcessing() {
    return flux -> flux
        .doOnNext(p -> p.setPrice(1.1 * p.getPrice()))
        .doOnNext(p -> p.setItem("{{" + p.getItem() + "}}"));

  }

  public static Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>> kidsProcessing() {
    return flux -> flux
        .doOnNext(p -> p.setPrice(0.5 * p.getPrice()))
        .flatMap(p -> Flux.concat(Mono.just(p), getFreeKidsOrder()));
  }

  private static Mono<PurchaseOrder> getFreeKidsOrder() {
    return Mono.fromSupplier(() -> {
      final PurchaseOrder purchaseOrder = new PurchaseOrder();
      purchaseOrder.setItem("FREE - " + purchaseOrder.getItem());
      purchaseOrder.setPrice(0);
      purchaseOrder.setCategory("Kids");
      return purchaseOrder;
    });
  }

}
