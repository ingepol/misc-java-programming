package com.multithreading.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec09Locks {
  
  /*
  ReentrantLock
  - it has the same behavior as the "synchronized approach"
  - of course it has some additional features
      new ReentrantLock(boolean fairness)
        if the fairness parameter is TRUE then the longest waiting thread will get the lock
        otherwise there is no access order.
       
       IMPORTANT: a good approach is to use try-catch-finally blocks when doing the critical 
       section and call unlock() in the final block 
   */

  private static int counter = 0;

  private static final Lock lock = new ReentrantLock();

  private static void increment() {
    lock.lock();
    try {
      for (int i = 0; i < 100000; i++) {
        counter++;
      }
    } finally {
      lock.unlock();
    }
  }

  public static void add() {
    lock.unlock();
  }

  public static void main(final String[] args) {
    final Thread t1 = new Thread(Lec09Locks::increment);
    final Thread t2 = new Thread(Lec09Locks::increment);
    t1.start();
    t2.start();
    try {
      t1.join();
    } catch (final InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    log.info("Counter: " + counter);
  }

}
