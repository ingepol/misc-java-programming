package com.multithreading.threads;

import com.common.utility.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec04DaemonAndUserThreads {

  // daemon threads are terminated by the JVM when all other worker threads are 
  // terminated (finish execution).  
  // So this is the main difference: worker threads are not terminated while 
  // daemon threads are interrupted by the JVM 

  public static void main(final String[] args) {
    final String nameThread = Thread.currentThread().getName();
    log.info(nameThread);

    final Thread t1 = new Thread(new DaemonWorker()); // It would be terminated when Normal Worker finish
    final Thread t2 = new Thread(new NormalWorker());
    t1.setDaemon(true);
    log.info("" + t1.isDaemon());
    t1.start();
    t2.start();
  }

  @Slf4j
  static class DaemonWorker implements Runnable {

    @Override
    public void run() {
      while (true) {
        ThreadUtil.sleepSeconds(1);
        log.info("Daemon thread is running");
      }
    }
  }

  @Slf4j
  static class NormalWorker implements Runnable {

    @Override
    public void run() {
      ThreadUtil.sleepSeconds(3);
      log.info("Normal thread finishes execution...");
    }
  }

}

