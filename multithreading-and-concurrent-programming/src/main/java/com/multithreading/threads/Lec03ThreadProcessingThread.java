package com.multithreading.threads;

import static com.common.utility.ThreadUtil.sleepMillis;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec03ThreadProcessingThread {

  public static void main(final String[] args) {
    // IT IS NOT PARALLEL EXECUTION!
    // achieve multi-threading
    final Thread t1 = new Thread1();
    final Thread t2 = new Thread2();
    t1.start();
    t2.start();

    try {
      t1.join(); //Wait for it to continue with next lines (log.info)
      t2.join(); //Wait for it to continue with next lines (log.info)
    } catch (final InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    log.info("Finished with threads");
  }

  @Slf4j
  static class Thread1 extends Thread {

    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        sleepMillis(100);
        log.info("Thread1: {}", i);
      }
    }
  }

  @Slf4j
  static class Thread2 extends Thread {

    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        sleepMillis(100);
        log.info("Thread2: {}", i);
      }
    }
  }
}

