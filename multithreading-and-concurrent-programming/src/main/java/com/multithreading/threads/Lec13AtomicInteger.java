package com.multithreading.threads;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec13AtomicInteger {

  private static final AtomicInteger counter = new AtomicInteger();

  public static void main(final String[] args) {

    final Thread t1 = new Thread(Lec13AtomicInteger::increment);
    final Thread t2 = new Thread(Lec13AtomicInteger::increment);
    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (final InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    log.info("Counter {}", counter);
  }

  public static void increment() {
    for (int i = 0; i < 10000; i++) {
      counter.incrementAndGet();
    }
  }

}
