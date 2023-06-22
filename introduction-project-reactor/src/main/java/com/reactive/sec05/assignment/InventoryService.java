package com.reactive.sec05.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class InventoryService {

  private final Map<String, Integer> db = new HashMap<>();

  public InventoryService() {
    this.db.put("Kids", 100);
    this.db.put("Automotive", 100);
  }

  public Consumer<PurchaseOrder> subscribeOrderStream() {
    return p -> this.db.computeIfPresent(p.getCategory(), (k, v) -> v - p.getQuantity());
  }

  public Flux<String> inventoryStream() {
    return Flux.interval(Duration.ofSeconds(2))
        .map(i -> this.db.toString());
  }

}
