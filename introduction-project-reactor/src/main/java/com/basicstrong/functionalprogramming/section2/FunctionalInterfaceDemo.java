package com.basicstrong.functionalprogramming.section2;

import com.basicstrong.functionalprogramming.section2.interfaces.MyFunInterface;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FunctionalInterfaceDemo {

  public static void main(String[] args) {
    MyFunInterface fun = () -> log.info("Hello to functional interface");
    fun.Method1();

    onTheFly(() -> log.info("Hello on the fly"));

  }

  public static void onTheFly(MyFunInterface fn) {
    fn.Method1();
  }

}
