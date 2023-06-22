package com.common.functional;

import com.common.interfaces.IWorker;

public class RunnableConsumer implements IRunnable {

  @Override
  public Runnable apply(final IWorker worker) {
    return () -> {
      try {
        worker.consume();
      } catch (final InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    };
  }
}
