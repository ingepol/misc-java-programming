package com.common.utility;

import com.github.javafaker.Faker;

public class FakerData {

  private static final Faker FAKER = Faker.instance();

  public static Faker faker() {
    return FAKER;
  }
}
