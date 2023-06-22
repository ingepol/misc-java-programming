package com.reactive.sec01;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakerDemo {

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      log.info(Faker.instance().name().fullName());
    }
  }
}
