package com.basicstrong.functionalprogramming.section1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyRunnable implements Runnable {

  @Override
  public void run() {
    log.info("Thread executed");
  }
}
