package com.basicstrong.functionalprogramming.section4;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Slf4j
public class FunctionPractice {

  public static void main(String[] args) {
    List<String> list = List.of("Kit", "Kat", "Shake");
    Function<String, Integer> fn = String::length;
    List<Integer> newList = map(list, fn);
    log.info(newList.toString());
  }

  private static <T, R> List<R> map(List<T> list, Function<T, R> fn) {
    List<R> newList = new ArrayList<>();
    for (T element : list) {
      newList.add(fn.apply(element));
    }
    return newList;
  }
}
