package com.reactive.sec05.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RevenueService {

  private final Map<String, Double> db = new HashMap<>();

  public RevenueService() {
    this.db.put("Kids", 0.0);
    this.db.put("Automotive", 0.0);
  }

  public Consumer<PurchaseOrder> subscribeOrderStream() {
    return p -> this.db.computeIfPresent(p.getCategory(), (k, v) -> v + p.getPrice());
  }

  public Flux<String> revenueStream() {
    return Flux.interval(Duration.ofSeconds(2))
        .map(i -> this.db.toString());
  }

}
