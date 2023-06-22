package com.multithreading.threads;

import static com.common.utility.ThreadUtil.sleepMillis;
import static com.common.utility.ThreadUtil.sleepSeconds;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec10Volatile {

  public static void main(final String[] args) {
    final Worker worker = new Worker();
    final Thread t1 = new Thread(worker);
    t1.start();
    sleepSeconds(3);
    worker.setTerminated(true);
    log.info("Algorithm is terminated");
  }

  @Getter
  @Setter
  static class Worker implements Runnable {

    //it will store in the main memory
    private boolean isTerminated;

    @Override
    public void run() {
      while (!isTerminated) {
        log.info("Working class is running");
        sleepMillis(500);
      }
    }
  }
}
