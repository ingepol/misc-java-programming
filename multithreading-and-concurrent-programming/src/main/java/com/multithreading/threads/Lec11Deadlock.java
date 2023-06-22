package com.multithreading.threads;

import static com.common.utility.ThreadUtil.sleepMillis;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec11Deadlock {

  private final Lock lock1 = new ReentrantLock();
  private final Lock lock2 = new ReentrantLock();
  
  public static void main(String[] args) {
    Lec11Deadlock deadlock = new Lec11Deadlock();
    
    new Thread(deadlock::worker1, "worker1").start();
    new Thread(deadlock::worker2, "worker2").start();
    
  }
  
  public void worker1(){
    lock1.lock();
    log.info("Worker 1 acquires the lock1...");
    sleepMillis(300);
    lock2.lock();
    log.info("Worker 1 acquires the lock2...");
    lock1.unlock();
    lock2.unlock();
  }

  public void worker2(){
    lock2.lock();
    log.info("Worker 2 acquires the lock2...");
    sleepMillis(300);
    lock1.lock();
    log.info("Worker 2 acquires the lock1...");
    lock1.unlock();
    lock2.unlock();
  }
  
}
