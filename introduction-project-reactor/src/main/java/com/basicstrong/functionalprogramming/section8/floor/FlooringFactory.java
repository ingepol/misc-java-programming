package com.basicstrong.functionalprogramming.section8.floor;

import com.basicstrong.functionalprogramming.section8.interfaces.Flooring;

import java.util.function.Supplier;

public class FlooringFactory {

  public static Flooring getFlooring(int minTempature, int maxTempture) {
    Supplier<Flooring> flooring;
    if (minTempature <= 5 && maxTempture <= 20) {
      flooring = WoodenFlooring::new;
    } else if (minTempature <= 5 && maxTempture <= 45) {
      flooring = CorkFlooring::new;
    } else {
      flooring = ConcreteFlooring::new;
    }

    return flooring.get();
  }
}
