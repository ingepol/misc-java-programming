package com.learnreactiveprograming.functional;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class FunctionalExample {

  public static void main(final String[] args) {
    final List<String> nameList = List.of("alex", "ben", "chloe", "adam", "adam");
    final List<String> newNameList = namesGreaterThanSize(nameList, 3);
    log.info(newNameList.toString());
  }

  private static List<String> namesGreaterThanSize(final List<String> nameList, final int i) {
    return nameList.stream()
        .filter(name -> name.length() > 3)
        .map(String::toUpperCase)
        .distinct()
        .sorted()
        .collect(Collectors.toList());
  }
}
