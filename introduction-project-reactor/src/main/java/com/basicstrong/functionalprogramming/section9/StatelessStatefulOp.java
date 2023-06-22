package com.basicstrong.functionalprogramming.section9;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class StatelessStatefulOp {

  public static void main(String[] args) {
    List<Integer> list = List.of(1, 2, 4, 5, 6, 7, 8);

    List<Integer> collect = list
        .parallelStream() // Don't show parallel stream with stateful methods
        .skip(2) // Stateful
        .limit(5) // Stateful
        .collect(Collectors.toList());
    collect.forEach(val -> log.info(String.valueOf(val)));
  }
}
