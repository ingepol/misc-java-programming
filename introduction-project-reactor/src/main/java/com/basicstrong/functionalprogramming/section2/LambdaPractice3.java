package com.basicstrong.functionalprogramming.section2;

import com.basicstrong.functionalprogramming.section2.interfaces.LengthOfString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LambdaPractice3 {

  public static void main(String[] args) {
    LengthOfString len = String::length;
    int length = len.length("BasicsStrong");
    log.info(String.valueOf(length));

    LengthOfString lemMultipleLine = str -> {
      int lenInside = str.length();
      log.info("The length of the given string is {}", lenInside);
      return lenInside;
    };
    length = lemMultipleLine.length("BasicsStrong Multiple Line");
    log.info(String.valueOf(length));
  }
}
