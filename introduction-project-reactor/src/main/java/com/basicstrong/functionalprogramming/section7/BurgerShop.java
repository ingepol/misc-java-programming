package com.basicstrong.functionalprogramming.section7;

import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class BurgerShop {

  Function<Burger, Burger> decoration;

  public Burger use(Burger baseBurger) {
    return decoration.apply(baseBurger);
  }

}
