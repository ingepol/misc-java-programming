package com.reactive.sec05;

import com.reactive.sec05.assignment.InventoryService;
import com.reactive.sec05.assignment.OrderService;
import com.reactive.sec05.assignment.RevenueService;
import lombok.extern.slf4j.Slf4j;

import static com.reactive.utils.ReactiveUtility.sleepSeconds;
import static com.reactive.utils.ReactiveUtility.subscriber;

/**
 * Order Service: Expose purchase orders periodically. Use a flux of Purchase Orders which will be pushing some POs every second.
 * <p>
 * Revenue Service: Interested in observing the PO, and it wants to do something. Might want to update it tables based on the price paid for
 * the PO
 * <p>
 * Inventory Service: Interested in observing the PO, and it wants to do something.
 */
@Slf4j
public class Lec06AssignmentDemo {

  public static void main(final String[] args) {
    final OrderService orderService = new OrderService();
    final RevenueService revenueService = new RevenueService();
    final InventoryService inventoryService = new InventoryService();

    //revenue and inv - observe the order item
    orderService.orderStream().subscribe(revenueService.subscribeOrderStream());
    orderService.orderStream().subscribe(inventoryService.subscribeOrderStream());

    inventoryService.inventoryStream().subscribe(subscriber("inventory"));
    revenueService.revenueStream().subscribe(subscriber("revenue"));

    sleepSeconds(60);

  }

}
