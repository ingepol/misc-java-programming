package com.threads.casestudy;

import java.util.ArrayList;
import java.util.List;

import com.common.utility.FakerData;
import com.common.utility.ThreadUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PoliceAndHackers {

  public static final int MAX_PASSWORD = 9999;

  public static void main(final String[] args) {
    final Vault vault = new Vault(FakerData.faker().random().nextInt(1, MAX_PASSWORD));
    final List<Thread> threads = new ArrayList<>();
    threads.add(new AscendingHackerThread(vault));
    threads.add(new DescendingHackerThread(vault));
    threads.add(new PoliceThread());
    for (final Thread thread : threads) {
      thread.start();
    }
  }

  @AllArgsConstructor
  private static class Vault {

    private int password;

    public boolean isValid(final int guess) {
      ThreadUtil.sleepMillis(5);
      return this.password == guess;
    }
  }

  private abstract static class HackerThread extends Thread {

    protected Vault vault;

    public HackerThread(final Vault vault) {
      this.vault = vault;
      this.setName(this.getClass().getSimpleName());
      this.setPriority(Thread.MAX_PRIORITY);
    }

    @Override
    public synchronized void start() {
      log.info("Starting thread {}", this.getName());
      super.start();
    }
  }

  private static class AscendingHackerThread extends HackerThread {

    public AscendingHackerThread(final Vault vault) {
      super(vault);
    }

    @Override
    public void run() {
      for (int guess = 0; guess < MAX_PASSWORD; guess++) {
        if (vault.isValid(guess)) {
          log.info("{} guessed the password {}", this.getName(), guess);
          System.exit(0);
        }
      }
    }
  }

  private static class DescendingHackerThread extends HackerThread {

    public DescendingHackerThread(final Vault vault) {
      super(vault);
    }

    @Override
    public void run() {
      for (int guess = MAX_PASSWORD; guess >= 0; guess--) {
        if (vault.isValid(guess)) {
          log.info("{} guessed the password {}", this.getName(), guess);
          System.exit(0);
        }
      }
    }
  }

  private static class PoliceThread extends Thread {

    @Override
    public void run() {
      for (int i = 10; i > 0; i--) {
        ThreadUtil.sleepSeconds(1);
        log.info("Second {}", i);
      }
      log.info("Game Over for you hacker");
      System.exit(0);
    }
  }
}
