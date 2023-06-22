package com.multithreading.threads;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec01SequentialProcessing {

  public static void main(final String[] args) {
    //Sequential programming
    final Runner1 runner1 = new Runner1();
    final Runner2 runner2 = new Runner2();
    runner1.execute();
    runner2.execute();
  }

  @Slf4j
  static class Runner1 {

    public void execute() {
      for (int i = 0; i < 10; i++) {
        log.info("Runner1: {}", i);
      }
    }
  }

  @Slf4j
  static class Runner2 {

    public void execute() {
      for (int i = 0; i < 10; i++) {
        log.info("Runner2: {}", i);
      }
    }
  }
}

