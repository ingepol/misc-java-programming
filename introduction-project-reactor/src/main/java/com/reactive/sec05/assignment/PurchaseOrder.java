package com.reactive.sec05.assignment;

import com.github.javafaker.Commerce;
import lombok.Data;
import lombok.ToString;

import static com.reactive.utils.FakerUtility.faker;

@Data
@ToString
public class PurchaseOrder {

  private String item;

  private double price;

  private String category;

  private int quantity;

  public PurchaseOrder() {
    final Commerce commerce = faker().commerce();
    this.item = commerce.productName();
    this.price = Double.parseDouble(commerce.price().replace(",", "."));
    this.category = commerce.department();
    this.quantity = faker().random().nextInt(1, 10);
  }
}
