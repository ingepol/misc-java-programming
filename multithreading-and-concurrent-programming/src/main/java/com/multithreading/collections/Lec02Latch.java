package com.multithreading.collections;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Lec02Latch {

  /**
   * This is used to synchronize one or more tasks by forcing them to wait for the completion of a set of
   * operations being performed by other tasks
   * - you give an initial count to a CountDownLatch object, and any task that calls await() on that
   * object will block until the count reaches zero.
   * - other tasks may call countDown() on the object to reduce the count, presumably when a task
   * finishes its job.
   * - A CountDownLatch is designed to be used in a one-shot fashion and the count cannot be reset!!!
   * - if you need a version than resets the count, you can use a  instead.
   * <p>
   * A typical use-case is to divide a problem into N independently solvable tasks and create a
   * CountDownLatch with a value of N.
   * <p>
   * When each task is finished it call countDown() on the latch. Tasks waiting for the problem to
   * be solved call await().
   * on the latch to hold themselves back until it is completed.
   */

  public static void main(final String[] args) {
    final CountDownLatch latch = new CountDownLatch(5);
    final ExecutorService service = Executors.newSingleThreadExecutor();
    for (int i = 0; i < 5; i++) {
      service.execute(new WorkerLatch(i, latch));
    }
    try {
      latch.await();
    } catch (final InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    log.info("All tasks have been finished...");
  }

}

@AllArgsConstructor
@Slf4j
class WorkerLatch implements Runnable {

  private int id;
  private CountDownLatch latch;

  @Override
  public void run() {
    this.doWork();
    this.latch.countDown();
  }

  private void doWork() {
    try {
      log.info("Thread with ID  " + this.id + " start working...");
      Thread.sleep(2000);
    } catch (final InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
} 
