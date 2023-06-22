package com.multithreading.threads;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec06ProblemWithSynchronized {

  public static int counter1 = 0;

  public static int counter2 = 0;

  // because App object has a single lock: this is why the methods can not
  // be executed "at the same time" - time slicing algorithm
  public static synchronized void increment1() {
    counter1++;
  }

  // usually it is not a good practice to use synchronized keyword
  public static void increment2() {
    // class level locking
    // rule of thumb: we synchronize blocks that are 100% necessary
    synchronized (Lec06ProblemWithSynchronized.class) {
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
