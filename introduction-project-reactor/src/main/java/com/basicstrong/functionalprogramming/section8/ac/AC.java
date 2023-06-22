package com.basicstrong.functionalprogramming.section8.ac;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AC {

  public void turnOn() {
    log.info("turning on AC");
  }

  public void turnOff() {
    log.info("turning off AC");
  }

  public void incTemp() {
    log.info("Increasing temperature");
  }

  public void decTemp() {
    log.info("Decreasing temperature");
  }

}
