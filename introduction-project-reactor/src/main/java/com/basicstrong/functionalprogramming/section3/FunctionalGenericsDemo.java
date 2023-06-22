package com.basicstrong.functionalprogramming.section3;

import com.basicstrong.functionalprogramming.section3.interfaces.FunctionalGenerics;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FunctionalGenericsDemo {

  public static void main(String[] args) {
    FunctionalGenerics<String, String> fn1 = s -> s.substring(1, 5);
    log.info(fn1.execute("BasicStrong"));

    FunctionalGenerics<String, Integer> fn2 = String::length;
    log.info(String.valueOf(fn2.execute("BasicStrong")));
  }

}
