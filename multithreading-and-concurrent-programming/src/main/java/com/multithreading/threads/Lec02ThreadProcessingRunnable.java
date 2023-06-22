package com.multithreading.threads;

import lombok.extern.slf4j.Slf4j;

/**
 * Usually using the Runnable interface approach is preferred.
 * <p>
 * if we extends Thread then we can’t extend any other class (usually a huge diasvantage) because in Java a given class can extends one
 * class exclusively
 * </p>
 * a class may implement more interfaces as well - so implementing the Runnable interface can do no harm in the software logic (now or in
 * the future)
 */
@Slf4j
public class Lec02ThreadProcessingRunnable {

  public static void main(final String[] args) {
    // IT IS NOT PARALLEL EXECUTION!
    // achieve multi-threading
    final Thread t1 = new Thread(new Runnable1());
    final Thread t2 = new Thread(new Runnable2());
    t1.start();
    t2.start();
  }

  @Slf4j
  static class Runnable1 implements Runnable {

    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        log.info("Runner1: {}", i);
      }
    }
  }

  @Slf4j
  static class Runnable2 implements Runnable {

    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        log.info("Runner2: {}", i);
      }
    }
  }
}

