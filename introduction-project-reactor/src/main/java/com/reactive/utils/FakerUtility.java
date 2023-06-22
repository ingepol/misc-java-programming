package com.reactive.utils;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FakerUtility {

  private static final Faker FAKER = Faker.instance();

  public static Faker faker() {
    return FAKER;
  }

}
