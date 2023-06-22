package com.multithreading.collections;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Slf4j
public class Lec01CollectionsSynchronization {


  public static void main(final String[] args) {

    // add() amd remove() method are synchronized
    // intrinsic lock - not that efficient because threads
    // have to wait fo each other even when the want to  execute
    // independent methods (operations) 
    final List<Integer> numberList = Collections.synchronizedList(new ArrayList<>());

    final Runnable runnable = () -> {
      for (int i = 0; i < 1000; i++) {
        numberList.add(i);
      }
    };

    final Thread t1 = new Thread(runnable);
    final Thread t2 = new Thread(runnable);

    t1.start();
    t2.start();
    try {
      t1.join();
      t2.join();
    } catch (final InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    log.info("Size of array: " + numberList.size());
  }
}

