package com.multithreading.executors;

import static com.common.utility.ThreadUtil.sleepSeconds;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec04StoppingExecutors {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    for (int i = 0; i < 100; i++) {
      executorService.execute(new Task(i+1));
    }
    // we prevent the executor to execute any further tasks
    executorService.shutdown();
    
    // terminate actual (running) tasks
    try{
      if(!executorService.awaitTermination(1, TimeUnit.SECONDS)){
        executorService.shutdownNow();
      } 
    }catch (InterruptedException e) {
      executorService.shutdownNow();
      Thread.currentThread().interrupt();
    }
  }
  
  @AllArgsConstructor
  public static class Task implements Runnable {

    private int id;
    
    @Override
    public void run() {
      log.info("Task with id {} is in work - thread id: {}", id, Thread.currentThread().getName());
      long duration = (long) (Math.random()*5);
      sleepSeconds(duration);
    }
  }
}
