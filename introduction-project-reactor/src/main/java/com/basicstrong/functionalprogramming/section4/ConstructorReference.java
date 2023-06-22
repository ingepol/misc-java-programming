package com.basicstrong.functionalprogramming.section4;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

@Slf4j
public class ConstructorReference {

  public static void main(String[] args) {
    Function<Runnable, Thread> threadGenerator = Thread::new;
    Runnable task1 = () -> log.info("Task 1 executed");
    Runnable task2 = () -> log.info("Task 2 executed");
    Thread thread1 = threadGenerator.apply(task1);
    Thread thread2 = threadGenerator.apply(task2);
    thread1.start();
    thread2.start();

    threadGenerator.apply(() -> log.info("Task 3 executed")).start();
  }
}
