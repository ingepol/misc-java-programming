package com.basicstrong.functionalprogramming.section12;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;

@Slf4j
public class CustomCollector {

  public static void main(String[] args) {
    List<Integer> numbers = List.of(2, 6, 8, 9, 0, 1, 52, 5, 61, 8, 9, 96, 0, 18, 23);
    Collector<Integer, List<Integer>, List<Integer>> toList = Collector.of(
        ArrayList::new, //Supplier
        List::add, // BiConsumer
        (list1, list2) -> {
          list1.addAll(list2);
          return list1;
        }, //BinaryOperator
        Characteristics.IDENTITY_FINISH
    );

    List<Integer> evens = numbers.stream()
        .filter(e -> e % 2 == 0)
        .collect(toList);

    evens.forEach(num -> log.info(String.valueOf(num)));

    log.info("---------------------");

    Collector<Integer, List<Integer>, List<Integer>> toSortedListCollector = Collector.of(
        ArrayList::new, //Supplier
        List::add, // BiConsumer
        (list1, list2) -> {
          list1.addAll(list2);
          return list1;
        }, //BinaryOperator
        list -> {
          Collections.sort(list);
          return list;
        }, //Function
        Characteristics.UNORDERED
    );

    List<Integer> sortedList = numbers.stream()
        .collect(toSortedListCollector);

    sortedList.forEach(num -> log.info(String.valueOf(num)));

  }
}
