package com.threads.interrupt;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Example1 {

  public static void main(String[] args) {
    Thread thread = new Thread(new BlockingTask());
    thread.start();
    thread.interrupt();
  }

  private static class BlockingTask implements Runnable {

    @Override
    public void run() {
      try {
        Thread.sleep(50000);
      } catch (InterruptedException e) {
        log.error("Exiting blocking thread");
        Thread.currentThread().interrupt();
      }
    }
  }


}
