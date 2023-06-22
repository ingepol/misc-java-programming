package com.reactive.sec09.assignment;

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

  public PurchaseOrder() {
    final Commerce commerce = faker().commerce();
    this.item = commerce.productName();
    this.price = Double.parseDouble(commerce.price().replace(",", "."));
    this.category = commerce.department();
  }
}
