package com.basicstrong.functionalprogramming.section4;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

@Slf4j
public class UnaryOperatorPractice {

  public static void main(String[] args) {
    List<Integer> list = List.of(10, 20, 30, 40, 50);
    UnaryOperator<Integer> operator = i -> i * 100;
    List<Integer> newList = mapper(list, operator);
    log.info(newList.toString());
  }

  private static <T> List<T> mapper(List<T> list, UnaryOperator<T> operator) {
    List<T> newList = new ArrayList<>();
    for (T element : list) {
      newList.add(operator.apply(element));
    }
    return newList;
  }
}
