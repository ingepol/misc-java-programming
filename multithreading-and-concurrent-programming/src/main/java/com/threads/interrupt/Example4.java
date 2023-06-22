package com.threads.interrupt;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Example4 {

  public static void main(String[] args) {
    Thread thread = new Thread(new SleepingThread());
    thread.start();
    thread.interrupt();
  }

  private static class SleepingThread implements Runnable {

    @Override
    public void run() {
      while (true) {
        try {
          Thread.sleep(1000000);
        } catch (InterruptedException e) {
          return;
        }
      }
    }
  }
}
