package com.multithreading.executors;

import static com.common.utility.ThreadUtil.sleepSeconds;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec01SingleThreadExecutor {

  public static void main(String[] args) {
    // it is a single thread that will execute the tasks sequentially
    // so one after another.
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    for (int i = 0; i < 5; i++) {
      executorService.execute(new Task(i));
    }
    // The thread don't finished because we have to shut down the executor
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
