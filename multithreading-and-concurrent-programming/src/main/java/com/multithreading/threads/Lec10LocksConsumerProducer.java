package com.multithreading.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.common.functional.IRunnable;
import com.common.functional.RunnableConsumer;
import com.common.functional.RunnableProducer;
import com.common.interfaces.IWorker;
import com.common.utility.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec10LocksConsumerProducer {
  
  /*
  ReentrantLock
  - it has the same behavior as the "synchronized approach"
  - of course it has some additional features
      new ReentrantLock(boolean fairness)
        if the fairness parameter is TRUE then the longest waiting thread will get the lock
        otherwise there is no access order.
       
       IMPORTANT: a good approach is to use try-catch-finally blocks when doing the critical 
       section and call unlock() in the final block 
   */

  static class Worker implements IWorker {

    private final Lock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();

    @Override
    public void produce() throws InterruptedException {
      lock.lock();
      log.info("Producer method...");
      //wait()
      condition.await();
      log.info("Again the producer method...");
    }

    @Override
    public void consume() throws InterruptedException {
      // "We want to make sure that the start with the producer"
      ThreadUtil.sleepSeconds(2);
      lock.lock();
      log.info("Consumer method...");
      ThreadUtil.sleepSeconds(3);
      //notify()
      condition.signal();
      lock.unlock();
    }

  }

  public static void main(final String[] args) {
    final Worker worker = new Worker();
    final IRunnable producer = new RunnableProducer();
    final IRunnable consumer = new RunnableConsumer();
    final Thread t1 = new Thread(producer.apply(worker));
    final Thread t2 = new Thread(consumer.apply(worker));
    t1.start();
    t2.start();
    try {
      t1.join();
      t2.join();
    } catch (final InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

}
