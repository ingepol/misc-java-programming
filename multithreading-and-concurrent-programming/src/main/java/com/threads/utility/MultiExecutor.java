package com.threads.utility;

import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {

  private final List<Thread> threads = new ArrayList<>();

  /*
   * @param tasks to executed concurrently
   */
  public MultiExecutor(List<Runnable> tasks) {
    tasks.forEach(runnable -> threads.add(new Thread(runnable)));
  }

  /**
   * Starts and executes all the tasks concurrently
   */
  public void executeAll() {
    this.threads.forEach(Thread::start);
  }
}
