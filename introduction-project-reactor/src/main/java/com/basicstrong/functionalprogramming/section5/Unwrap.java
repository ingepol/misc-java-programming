package com.basicstrong.functionalprogramming.section5;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class Unwrap {

  public static void main(String[] args) {
    Integer a = 10;
    Optional<Integer> optional = Optional.of(a);

    Integer integerVal = optional.get();
    log.info(String.valueOf(integerVal));

    Optional<Integer> emptyOptional = Optional.empty();
    //emptyOptional.get();

    Integer val = emptyOptional.isPresent() ? emptyOptional.get() : 0;
    log.info(String.valueOf(val));

    //orElse,m orElseGet

    Integer orElse = emptyOptional.orElse(0);
    log.info(String.valueOf(orElse));

    Integer orElseGet = emptyOptional.orElseGet(() -> 0);
    log.info(String.valueOf(orElseGet));

    //orElseThrow

    Integer orElseThrow = emptyOptional.orElseThrow(() -> new IllegalArgumentException());

    //orElseThrow() = get()

  }

}
