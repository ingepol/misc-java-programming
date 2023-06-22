package com.basicstrong.functionalprogramming.section4;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Consumer;

@Slf4j
public class ConsumerPractice {

  public static void main(String[] args) {
    List<Integer> list = List.of(34, 67, 8, 23, 67, 89, 90);
    Consumer<Integer> consumer = e -> log.info(String.valueOf(e));
    consumer.accept(56);
    printElements(list, consumer);
  }

  private static <T> void printElements(List<T> list, Consumer<T> consumer) {
    for (T t : list) {
      consumer.accept(t);
    }
  }
}
