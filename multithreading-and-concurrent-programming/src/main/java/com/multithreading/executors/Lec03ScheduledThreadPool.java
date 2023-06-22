package com.multithreading.executors;

import static com.common.utility.ThreadUtil.sleepSeconds;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec03ScheduledThreadPool {

  public static void main(String[] args) {
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    executor.scheduleAtFixedRate(new StockMarkerUpdater(), 1000, 5000, TimeUnit.MILLISECONDS);
    // The thread don't finished because we have to shut down the executor
  }
  
  public static class StockMarkerUpdater implements Runnable {
    
    @Override
    public void run() {
      log.info("Updating and downloading stock related data from data web ...");
    }
  }
}
