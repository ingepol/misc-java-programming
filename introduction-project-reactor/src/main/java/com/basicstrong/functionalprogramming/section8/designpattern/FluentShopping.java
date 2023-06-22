package com.basicstrong.functionalprogramming.section8.designpattern;

import com.basicstrong.functionalprogramming.section8.order.FluentOrder;
import com.basicstrong.functionalprogramming.section8.order.Order;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FluentShopping {

  public static void main(String[] args) {
    /*
      A fluent interface provides an easy-readable, flowing interface, 
      that often mimics a domain specific language.  Using this pattern 
      results in code that can be read nearly as human language
     */

    Order myOrder = new Order();
    myOrder.add("Shoes");
    myOrder.add("Headphones");
    myOrder.deliverAt("Street no 45, Jodhpur");
    log.info(myOrder.place());

    String place = FluentOrder.place(order -> order
        .add("Shoes")
        .add("Headphones")
        .deliverAt("Street no 45, Jodhpur"));
    log.info(place);

  }
}
