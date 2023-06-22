package com.multithreading.threads;

import com.common.utility.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec07WaitAndNotify {

  public static void main(final String[] args) {
    final Process process = new Process();

    final Thread t1 = new Thread(() -> {
      try {
        process.produce();
      } catch (final InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    });

    final Thread t2 = new Thread(process::consume);

    t1.start();
    t2.start();
  }

  @Slf4j
  static class Process {

    public void produce() throws InterruptedException {
      synchronized (this) {
        log.info("Running the produce method...");
        wait();
        log.info("Again in the produce method...");
      }
    }

    public void consume() {
      ThreadUtil.sleepSeconds(1);
      synchronized (this) {
        log.info("Consume method is executed...");
        notifyAll();
        //it is not going to handle the lock: we can make further operations.
        ThreadUtil.sleepSeconds(5);
      }
    }
  }
}

