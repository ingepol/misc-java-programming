package com.common.interfaces;

public interface IWorker {

  void produce() throws InterruptedException;

  void consume() throws InterruptedException;

}
