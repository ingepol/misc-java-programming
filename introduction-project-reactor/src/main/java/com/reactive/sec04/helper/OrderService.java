package com.reactive.sec04.helper;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {

  private static Map<Integer, List<PurchaseOrder>> db = new HashMap<>();

  static {
    final List<PurchaseOrder> list1 = Arrays.asList(
        new PurchaseOrder(1),
        new PurchaseOrder(1),
        new PurchaseOrder(1)
    );
    final List<PurchaseOrder> list2 = Arrays.asList(
        new PurchaseOrder(2),
        new PurchaseOrder(2)
    );
    db.put(1, list1);
    db.put(2, list2);
  }

  public static Flux<Object> getOrders(final int userId) {
    return Flux.create(purchaseOrderFluxSink -> {
      db.get(userId).forEach(purchaseOrderFluxSink::next);
      purchaseOrderFluxSink.complete();
    }).delayElements(Duration.ofSeconds(1));
  }
}
