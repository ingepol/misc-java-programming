package com.basicstrong.functionalprogramming.section8.order;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class Order {

  private List<String> cart = new ArrayList<>();
  private String address = "";

  public void add(String item) {
    cart.add(item);
  }

  public void deliverAt(String location) {
    this.address = location;
  }

  public String place() {
    return String.format("%s items ordered by you will be delivered at %s by tomorrow.",
        this.cart.size(), this.address);
  }

}
