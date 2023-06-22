package com.reactive.sec09;

import com.reactive.sec09.assignment.OrderProcessor;
import com.reactive.sec09.assignment.OrderService;
import com.reactive.sec09.assignment.PurchaseOrder;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static com.reactive.utils.ReactiveUtility.sleepSeconds;
import static com.reactive.utils.ReactiveUtility.subscriber;

/**
 * Categories Kids/Automotive
 * <p>Going do two different process</p>
 * <p>Automotive apply 10% tax</p>
 * <p>Kids apply 50% discount</p>
 */
@Slf4j
public class Lec06AssignmentOrderProcessing {

  public static void main(final String[] args) {

    final Map<String, Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>>> map = Map.of(
        "Kids", OrderProcessor.kidsProcessing(),
        "Automotive", OrderProcessor.automotiveProcessing()
    );

    final Set<String> set = map.keySet();

    OrderService.orderStream()
        .filter(po -> set.contains(po.getCategory()))
        .groupBy(PurchaseOrder::getCategory)
        .flatMap(gf -> map.get(gf.key()).apply(gf))
        .subscribe(subscriber());

    sleepSeconds(60);
  }


}
