package com.basicstrong.functionalprogramming.section5;

import java.util.Optional;

public class OptionalCreation {

  public static void main(String[] args) {
    String val = "A Sting";
    Optional<String> optional = Optional.of(val);

    Optional<Integer> empty = Optional.empty();

    Optional<String> nullable = Optional.ofNullable(val);
    Optional<String> emptyOptional = Optional.ofNullable(null);

  }

}
