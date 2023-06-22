package com.threads.join;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JoinDemo {

  public static void main(String[] args) throws InterruptedException {
    List<Long> inputNumbers = Arrays.asList(1000000000L, 3435L, 35435L, 2324L, 23L, 4656L, 5556L);
    List<FactorialThread> threads = new ArrayList<>();
    inputNumbers.forEach(inputNumber -> threads.add(new FactorialThread(inputNumber)));
    for (FactorialThread thread : threads) {
      thread.setDaemon(true);
      thread.start();
      thread.join(2000);
    }
    for (int i = 0; i < inputNumbers.size(); i++) {
      FactorialThread factorialThread = threads.get(i);
      Long number = inputNumbers.get(i);
      if (factorialThread.isFinished()) {
        log.info("Factorial of {} is {}", number, factorialThread.getResult());
      } else {
        log.info("The calculation for {} is still in progress", number);
      }
    }
  }
}
