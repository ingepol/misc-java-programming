package com.basicstrong.functionalprogramming.section8.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FluentOrder {

  private List<String> cart = new ArrayList<>();
  private String address = "";

  public FluentOrder add(String item) {
    this.cart.add(item);
    return FluentOrder.builder().cart(this.cart).address(this.address).build();
  }

  public FluentOrder deliverAt(String location) {
    this.address = location;
    return FluentOrder.builder().cart(this.cart).address(this.address).build();
  }

  public static String place(UnaryOperator<FluentOrder> function) {
    FluentOrder order = new FluentOrder();
    order = function.apply(order);
    return String.format("%s items ordered by you will be delivered at %s by tomorrow.",
        order.cart.size(), order.address);
  }

}
