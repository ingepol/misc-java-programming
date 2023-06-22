package com.reactive.sec04.helper;

import lombok.Data;
import lombok.ToString;

import static com.reactive.utils.FakerUtility.faker;

@Data
@ToString
public class User {

  private int userId;

  private String name;

  public User(final int userId) {
    this.userId = userId;
    this.name = faker().name().fullName();
  }

}
