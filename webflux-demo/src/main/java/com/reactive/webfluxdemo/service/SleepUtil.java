package com.reactive.webfluxdemo.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SleepUtil {
  
  public static void sleepSeconds(long seconds){
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      log.error("Something was wrong while sleeping");
      Thread.currentThread().interrupt();
    }
  }
}
