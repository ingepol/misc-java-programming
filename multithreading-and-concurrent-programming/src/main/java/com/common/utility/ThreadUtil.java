package com.common.utility;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ThreadUtil {

  public static void sleepSeconds(final long seconds) {
    sleepMillis(seconds * 1000);
  }

  public static void sleepMillis(final long millis) {
    try {
      Thread.sleep(millis);
    } catch (final InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
