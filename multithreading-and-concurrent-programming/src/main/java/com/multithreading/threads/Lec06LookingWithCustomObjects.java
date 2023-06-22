package com.multithreading.threads;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec06LookingWithCustomObjects {

  public static int counter1 = 0;

  public static int counter2 = 0;

  private static final Object lock1 = new Object();

  private static final Object lock2 = new Object();

  public static synchronized void increment1() {
    // at the same time != parallel - CPU time slicing
    synchronized (lock1) {
      counter1++;
    }
  }

  public static void increment2() {
    // in this way the thread can execute increment to you without waiting for the 
    // firs strategy to finish with increment one method
    synchronized (lock2) {
      counter2++;
    }
  }

  public static void process() {
    final Thread t1 = new Thread(() -> {
      for (int i = 0; i < 100; i++) {
        increment1();
      }
    });

    final Thread t2 = new Thread(() -> {
      for (int i = 0; i < 100; i++) {
        increment2();
      }
    });
    t1.start();
    t2.start();
    try {
      t1.join();
      t2.join();
    } catch (final InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    log.info("The counter is: " + counter1);
    log.info("The counter is: " + counter2);
  }

  public static void main(final String[] args) {
    process();
  }
}
