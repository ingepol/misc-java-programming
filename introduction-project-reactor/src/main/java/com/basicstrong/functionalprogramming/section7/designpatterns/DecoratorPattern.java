package com.basicstrong.functionalprogramming.section7.designpatterns;

import com.basicstrong.functionalprogramming.section7.Burger;
import com.basicstrong.functionalprogramming.section7.BurgerShop;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DecoratorPattern {

  public static void main(String[] args) {
    /*
    It is used to modify functionality of object at runtime without affecting other instances
    of the same class
     */
    Burger myOrder = new BurgerShop(Burger::addVegies).use(new Burger());
    myOrder = new BurgerShop(Burger::addCheese).use(myOrder);
    log.info("I get" + myOrder.toString());
  }
}
