package com.functional.style.imperativevsdeclarative;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Lec02Example {

  public static void main(final String[] args) {
    final List<Integer> integerList = Arrays.asList(1, 2, 3, 3, 4, 5, 5, 6, 7, 8, 8, 9, 10);

    // Imperative
    final List<Integer> uniqueList = new ArrayList<>();
    for (final Integer num : integerList) {
      if (!uniqueList.contains(num)) {
        uniqueList.add(num);
      }
    }
    log.info("Declarative:\t{}", uniqueList.toString());

    // Declarative
    final List<Integer> uniqueListDeclarative = integerList.stream()
        .distinct()
        .collect(Collectors.toList());
    log.info("Imperative:\t{}", uniqueListDeclarative.toString());
  }
}
