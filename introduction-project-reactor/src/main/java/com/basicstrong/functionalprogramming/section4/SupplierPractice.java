package com.basicstrong.functionalprogramming.section4;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

@Slf4j
public class SupplierPractice {

  public static void main(String[] args) {
    Supplier<String> stringSupplier = () -> "A String";
    log.info(stringSupplier.get());
    //Could be use DoubleSupplier instead Supplier<Double>
    Supplier<Double> randomNumber = Math::random;
    log.info(String.valueOf(randomNumber.get()));
  }

}
