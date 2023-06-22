package com.basicstrong.functionalprogramming.section7;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class Burger {

  private String burgerType;

  public Burger() {
    this.burgerType = "";
  }

  public Burger addVegies() {
    this.burgerType += " Vegie";
    return new Burger(this.burgerType);
  }

  public Burger addCheese() {
    this.burgerType += " Cheese";
    return new Burger(this.burgerType);
  }

  @Override
  public String toString() {
    return String.format("%s", burgerType + " burger");
  }
}
