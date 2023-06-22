package com.functional.style.lambdas;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec01Runnable {

  public static void main(final String[] args) {
    // prior java 8
    final Runnable runnable = new Runnable() {
      @Override
      public void run() {
        log.info("Inside Runnable");
      }
    };

    new Thread(runnable).start();

    // Java 8 lambda

    final Runnable runnableLambda = () -> log.info("Inside runnable lambda");
    new Thread(runnableLambda).start();

    final Runnable runnableLambdaMultipleStm = () -> {
      log.info("Inside runnable lambda, first statement");
      log.info("Inside runnable lambda, second statement");
    };
    new Thread(runnableLambdaMultipleStm).start();

  }
}
