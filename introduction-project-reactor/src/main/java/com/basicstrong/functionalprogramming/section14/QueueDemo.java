package com.basicstrong.functionalprogramming.section14;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QueueDemo {

  public static void main(String[] args) {
    QueueFun<String> q = QueueFun.queue();
    QueueFun<String> enqueue = q.enqueue("Hey").enqueue(" How are you?").enqueue(" Fine?");
    log.info("enqueue");
    enqueue.forEach(log::info);
    QueueFun<String> dequeue = enqueue.dequeue();
    log.info("dequeue");
    dequeue.forEach(log::info);
    log.info("peek");
    log.info(dequeue.peek());
    log.info("isEmpty");
    log.info(String.valueOf(dequeue.isEmpty()) + " the queue still have " + dequeue.size()
        + " elements");

  }
}
