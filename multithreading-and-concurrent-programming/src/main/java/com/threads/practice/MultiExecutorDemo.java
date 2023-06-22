package com.threads.practice;

import com.threads.utility.MultiExecutor;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MultiExecutorDemo {

  public static void main(String[] args) {
    List<Runnable> runnableList = List.of(
        () -> log.info("Runnable 1"),
        () -> log.info("Runnable 2"),
        () -> log.info("Runnable 3")
    );
    MultiExecutor multiExecutor = new MultiExecutor(runnableList);
    multiExecutor.executeAll();
  }
}
