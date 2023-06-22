package com.basicstrong.functionalprogramming.section13;

import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class SetFunctionalOperations {

  private static final String SEPARATOR_LINE = "-----------------";

  public static void main(String[] args) {
    Set<Integer> set = Set.of(3, 56, 7, 82, 39);

    //traversal
    set.forEach(SetFunctionalOperations::printInt);
    log.info(SEPARATOR_LINE);

    //Filter
    set.stream()
        .filter(e -> e % 2 == 0)
        .forEach(SetFunctionalOperations::printInt);
    log.info(SEPARATOR_LINE);

    //Sorting
    set.stream()
        .sorted()
        .forEach(SetFunctionalOperations::printInt);
    log.info(SEPARATOR_LINE);

    Stream<Integer> sorted = set.stream()
        .sorted();
    sorted.forEach(SetFunctionalOperations::printInt);
    log.info(SEPARATOR_LINE);

    TreeSet<Integer> sortedSet = new TreeSet<>(set);
    sortedSet.forEach(SetFunctionalOperations::printInt);
    log.info(SEPARATOR_LINE);

    //Map
    Set<Double> hashSet = set.stream()
        .map(Double::valueOf)
        .collect(Collectors.toSet());
    hashSet.forEach(SetFunctionalOperations::printDouble);
    log.info(SEPARATOR_LINE);

    //Reduce
    int sum = set.stream()
        .mapToInt(e -> e)
        .sum();
    printInt(sum);

  }

  private static void printInt(Integer num) {
    log.info(String.valueOf(num));
  }

  private static void printDouble(Double num) {
    log.info(String.valueOf(num));
  }
}
