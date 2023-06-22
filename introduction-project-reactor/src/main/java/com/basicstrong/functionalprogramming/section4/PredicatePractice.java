package com.basicstrong.functionalprogramming.section4;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Slf4j
public class PredicatePractice {

  public static void main(String[] args) {

    List<String> list = List.of("Basics", "", "Strong", "", "BasicsStrong");
    Predicate<String> predicate = s -> !s.isEmpty();
    List<String> newList = filterList(list, predicate);
    Predicate<String> filter = s -> s.contains("Basics");
    List<String> filteredList = filterList(list, filter);

    List<Integer> intList = List.of(1, 4, 6, 7, 8);
    Predicate<Integer> integerFilter = e -> e % 2 == 0;
    List<Integer> integers = filterList(intList, integerFilter);

    log.info(newList.toString());
    log.info(filteredList.toString());
    log.info(integers.toString());

  }

  private static <T> List<T> filterList(List<T> list, Predicate<T> predicate) {
    List<T> newList = new ArrayList<>();
    for (T element : list) {
      if (predicate.test(element)) {
        newList.add(element);
      }
    }
    return newList;
  }
}
