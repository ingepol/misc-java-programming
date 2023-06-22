package com.multithreading.executors;

import static com.common.utility.ThreadUtil.sleepSeconds;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec05CallableAndFuture {

  public static void main(final String[] args) {
    final ExecutorService service = Executors.newFixedThreadPool(2);
    final List<Future<String>> futures = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      final Future<String> future = service.submit(new Processor(i + 1));
      futures.add(future);
    }

    futures.forEach(future -> {
      try {
        log.info(future.get());
      } catch (final InterruptedException | ExecutionException e) {
        Thread.currentThread().interrupt();
      }
    });
  }

  @AllArgsConstructor
  static class Processor implements Callable<String> {

    private int id;

    @Override
    public String call() throws Exception {
      sleepSeconds(2);
      return "id: " + id;
    }
  }
}
