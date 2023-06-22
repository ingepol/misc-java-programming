package com.multithreading.threads;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec05MemoryManagement {

  public static int counter = 0;

  // we have to make sure this method is executed only by a single thread
  // at given time
  public static synchronized void increment() {
    counter++;
  }

  public static void process() {
    final Thread t1 = new Thread(() -> {
      for (int i = 0; i < 100; i++) {
        increment();
      }
    });

    final Thread t2 = new Thread(() -> {
      for (int i = 0; i < 100; i++) {
        increment();
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
    log.info("The counter is: " + counter);
  }

  public static void main(final String[] args) {
    //STACK_MEMORY local variable, and methods arguments as well as method calls are stored.
    //HEAP_MEMORY objects are stored on the heap and live as long as it is referenced.
    // Every thread has its own stack memory but all threads share the heap memory
    process();
  }
}
