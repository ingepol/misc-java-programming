package com.basicstrong.functionalprogramming.section1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LambdaExample1 {

  public static void main(String[] args) {
    /*
      // First way
      MyRunnable runnable = new MyRunnable();
      Thread t = new Thread(runnable);
    */
    /*
    // Second way
    Thread t = new Thread(new Runnable() {
      @Override
      public void run() {
        log.info("Thread Executed!");
      }
    });
     */
    // Finally and the best way, using lambda!!!
    Thread t = new Thread(() -> log.info("Thread Executed!"));
    t.start();
  }
}
