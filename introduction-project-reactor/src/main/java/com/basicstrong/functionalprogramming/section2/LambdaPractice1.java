package com.basicstrong.functionalprogramming.section2;

import com.basicstrong.functionalprogramming.section2.interfaces.Name;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LambdaPractice1 {

  public static void main(String[] args) {
    Name name = () -> log.info("Paul Arenas");
    name.myName();
  }

}
