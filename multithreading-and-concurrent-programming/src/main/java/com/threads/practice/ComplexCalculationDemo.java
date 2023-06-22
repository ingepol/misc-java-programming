package com.threads.practice;

import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ComplexCalculationDemo {

  public static void main(String[] args) throws InterruptedException {
    ComplexCalculation complexCalculation = new ComplexCalculation();
    BigInteger bigInteger = complexCalculation.calculateResult(
        BigInteger.valueOf(10),
        BigInteger.valueOf(2),
        BigInteger.valueOf(25),
        BigInteger.valueOf(3)
    );
    log.info("The result was {}", bigInteger.toString());
  }
}
