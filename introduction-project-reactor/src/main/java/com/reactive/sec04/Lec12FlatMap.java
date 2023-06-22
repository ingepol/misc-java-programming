package com.reactive.sec04;

import com.reactive.sec04.helper.OrderService;
import com.reactive.sec04.helper.PurchaseOrder;
import com.reactive.sec04.helper.UserService;

import static com.reactive.utils.ReactiveUtility.sleepSeconds;
import static com.reactive.utils.ReactiveUtility.subscriber;

public class Lec12FlatMap {

  public static void main(final String[] args) {
    UserService.getUsers()
        //.map(user -> OrderService.getOrders(user.getUserId()))
        .flatMap(user -> OrderService.getOrders(user.getUserId()))
        .map(PurchaseOrder.class::cast)
        .filter(p -> p.getPrice() > 10)
        .subscribe(subscriber());

    sleepSeconds(60);
  }
}
