package com.multithreading.threads;

import java.util.ArrayList;
import java.util.List;

import com.common.utility.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec08ProducerAndConsumer {

  public static void main(final String[] args) {
    final Processor processor = new Processor();
    final Thread t1 = new Thread(() -> {
      try {
        processor.producer();
      } catch (final InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    });
    final Thread t2 = new Thread(() -> {
      try {
        processor.consumer();
      } catch (final InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    });
    t1.start();
    t2.start();
  }

  static class Processor {

    private final List<Integer> list = new ArrayList<>();

    private static final int UPPER_LIMIT = 5;

    private static final int LOWER_LIMIT = 0;

    private final Object lock = new Object();

    private static int value = 0;

    public void producer() throws InterruptedException {
      synchronized (lock) {
        while (true) {
          if (list.size() == UPPER_LIMIT) {
            log.info("Waiting for removing items...");
            lock.wait();
          } else {
            log.info("Adding: " + value);
            list.add(value);
            value++;
            // we can call the notify - because the other thread will be notified 
            // only when it is in a waiting state.
            lock.notifyAll();
            // do other operations, that's means continue to add items unit the list size 
            // has UPPER_LIMIT items and wait
          }
          ThreadUtil.sleepMillis(500);
        }
      }
    }

    public void consumer() throws InterruptedException {
      synchronized (lock) {
        while (true) {
          if (list.size() == LOWER_LIMIT) {
            log.info("Waiting for adding items...");
            value = 0;
            lock.wait();
          } else {
            log.info("Removing: " + list.remove(list.size() - 1));
            lock.notifyAll();
            // do further operations !!! 
            // that's means continue to remove items until the list size has LOWER_LIMIT items and wait.
          }
          ThreadUtil.sleepMillis(500);
        }
      }
    }
  }
}
