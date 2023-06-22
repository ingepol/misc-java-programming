package com.reactive.sec04.helper;

import lombok.Data;
import lombok.ToString;

import static com.reactive.utils.FakerUtility.faker;

@Data
@ToString
public class PurchaseOrder {

  private String item;

  private Double price;

  private int userId;

  public PurchaseOrder(final int userId) {
    this.userId = userId;
    this.item = faker().commerce().productName();
    this.price = Double.parseDouble(faker().commerce().price().replace(",", "."));
  }
}
