package com.basicstrong.functionalprogramming.section5;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class Operations {

  public static void main(String[] args) {
    Optional<String> optional = Optional.of("Value");
    Optional<String> optionalEmpty = Optional.empty();
    //map()
    Optional<String> map = optional.map(val -> "Replaced");
    log.info(map.get()); // Could be not present (empty)

    String orlElse = optionalEmpty.map(val -> "Replaced").orElse("Empty");
    log.info(orlElse);

    //Filter
    Optional<String> filter = optional.filter(val -> val.equalsIgnoreCase("Value"));
    log.info(filter.get());

    //flatMap()
    Optional<String> flatMap = optional.flatMap(val -> Optional.of("Replaced by FlatMap"));
    log.info(flatMap.get());

  }
}
