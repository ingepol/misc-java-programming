package com.basicstrong.functionalprogramming.section5;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class MoreOperations {

  public static void main(String[] args) {
    Optional<String> optional = Optional.of("Value");
    optional.ifPresent(val -> log.info("This is a val: " + val));

    Optional.empty()
        .ifPresentOrElse(val -> log.info(String.valueOf(val)), () -> log.info("Values is absent"));

    //Stream
    optional.stream().forEach(log::info);
    Optional.empty().stream().forEach(val -> log.info(String.valueOf(val)));

    //or
    optional.or(() -> Optional.of("New Value")).ifPresent(log::info);
    Optional.empty().or(() -> Optional.of("New Value or"))
        .ifPresent(val -> log.info(String.valueOf(val)));

    //equals
    //optional
    //either both are empty
    //or if the values in optional are equal to each via equals method

    log.info(String.valueOf(optional.equals(Optional.of("Value"))));

    //HashCode
    log.info(String.valueOf(optional.hashCode())); // if hashCode is empty the result is zero


  }
}
