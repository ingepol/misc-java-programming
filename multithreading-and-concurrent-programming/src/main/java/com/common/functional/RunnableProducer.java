package com.common.functional;

import com.common.interfaces.IWorker;

public class RunnableProducer implements IRunnable {

  @Override
  public Runnable apply(final IWorker worker) {
    return () -> {
      try {
        worker.produce();
      } catch (final InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    };
  }
}
