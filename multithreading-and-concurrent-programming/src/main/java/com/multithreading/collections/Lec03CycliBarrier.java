package com.multithreading.collections;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Lec03CycliBarrier {

  /**
   * Latch -> a single thread can wait for other threads.
   * CycliBarrier -> multiple threads can wait for each other
   * <p>
   * A CycliBarrier is used in situation where you want to create a group of tasks to preform work in a
   * concurrent manner + wait until they are all finished before moving on to the next step.
   * -> something like join()
   * -> something like countDownLatch
   * <p>
   * CountDownLatch: one-shot event.
   * CycliBarrier: it can be reused over and over again.
   * + CycliBarrier has a barrier action: a runnable, that will run automatically when the count
   * reaches 0 !!
   * new CycliBarrrier(N) -> N threads will wait for each other.
   * <p>
   * WE CAN NOT REUSE LATCHES BUT WE CAN REUSE CycliBarriers --> reset() !!!
   */

  public static void main(final String[] args) {
    final ExecutorService service = Executors.newFixedThreadPool(5);
    final CyclicBarrier barrier = new CyclicBarrier(5, () -> log.info("All task have been finished..."));

    for (int i = 0; i < 5; i++) {
      service.execute(new WorkerBarrier(i + 1, barrier));
    }
    service.shutdownNow();
  }
}

@Slf4j
class WorkerBarrier implements Runnable {

  private final int id;
  private final CyclicBarrier barrier;
  private final Random random;

  public WorkerBarrier(final int id, final CyclicBarrier barrier) {
    this.id = id;
    this.barrier = barrier;
    this.random = new Random();
  }

  @Override
  public void run() {
    this.doWork();
  }

  private void doWork() {
    log.info("Thread with ID " + this.id + " starts the works....");
    try {
      Thread.sleep(this.random.nextInt(3000));
    } catch (final InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    try {
      this.barrier.await();
    } catch (final InterruptedException | BrokenBarrierException e) {
      Thread.currentThread().interrupt();
    }

    log.info("After the await()...");
  }
}
