package com.multithreading.threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec12LiveLock {

  private final Lock lock1 = new ReentrantLock();

  private final Lock lock2 = new ReentrantLock();

  public static void main(final String[] args) {
    final Lec12LiveLock liveLock = new Lec12LiveLock();

    new Thread(liveLock::worker1, "worker1").start();
    new Thread(liveLock::worker2, "worker2").start();

  }

  public void worker1() {
    while (true) {
      try {
        lock1.tryLock(50, TimeUnit.MICROSECONDS);
      } catch (final InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      log.info("Worker 1 acquires the lock1...");
      log.info("Worker 1 tries to get lock2...");

      if (lock2.tryLock()) {
        log.info("Worker 1 acquires the lock2...");
        lock2.unlock();
      } else {
        log.info("Worker 1 can not acquires lock2...");
        continue;
      }
      break;
    }
    lock1.unlock();
    lock2.unlock();
  }

  public void worker2() {
    while (true) {
      try {
        lock2.tryLock(50, TimeUnit.MICROSECONDS);
      } catch (final InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      log.info("Worker 2 acquires the lock2...");
      log.info("Worker 2 tries to get lock1...");

      if (lock1.tryLock()) {
        log.info("Worker 2 acquires the lock1...");
        lock1.unlock();
      } else {
        log.info("Worker 2 can not acquires lock1...");
        continue;
      }
      break;
    }
    lock1.unlock();
    lock2.unlock();
  }
}
