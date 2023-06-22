package com.multithreading.threads;

import static com.common.utility.ThreadUtil.sleepSeconds;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import lombok.extern.slf4j.Slf4j;

/**
 * It is used to control access to a shared resource that uses a counter variable.
 * <p>// semaphore maintains a set of permits
 * <p>- acquire() ... if a permit is available then takes it.
 * <p>- release() ...adds a permit
 * <p>    Semaphore just keeps a count of the number of permits available
 * <p>      new Semaphore(int permits, boolean fair) !!!
 */
@Slf4j
public class Lec14Semaphores {

  public static void main(final String[] args) {
    // Create multiple threads -- executors
    final ExecutorService service = Executors.newCachedThreadPool();
    for (int i = 0; i < 12; i++) {
      service.execute(Downloader.INSTANCE::download);
    }

  }

  // Singleton pattern
  enum Downloader {
    INSTANCE;

    private final Semaphore semaphore = new Semaphore(5, true);

    public void download() {
      try {
        semaphore.acquire();
        downloadData();
      } catch (final InterruptedException e) {
        Thread.currentThread().interrupt();
      } finally {
        semaphore.release();
      }
    }

    public void downloadData() {
      log.info("Downloading data from the web...");
      sleepSeconds(2);
    }

  }
}
