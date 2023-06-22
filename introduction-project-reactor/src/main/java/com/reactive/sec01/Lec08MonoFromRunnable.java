package com.reactive.sec01;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import static com.reactive.utils.ReactiveUtility.*;

@Slf4j
public class Lec08MonoFromRunnable {

  public static void main(final String[] args) {

    //Runnable don't return anything, but it's useful when we wat to be notified 
    // when the operation is complete
    final Runnable runnable = () -> log.info("");

    Mono.fromRunnable(timeConsumingProcess()).subscribe(
        onNext(),
        onError(),
        () -> log.info("process is done. Sending emails.")
        //It's executed as son as runnable is done.
    );

  }

  private static Runnable timeConsumingProcess() {
    return () -> {
      sleepSeconds(3);
      log.info("Operation Completed!");
    };
  }


}
