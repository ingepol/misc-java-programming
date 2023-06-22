package com.threads.interrupt;

import java.math.BigInteger;

import com.common.utility.ThreadUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Example2 {

  public static void main(final String[] args) {
    final LongComputationTask longComputationTask = new LongComputationTask(BigInteger.valueOf(200000),
        BigInteger.valueOf(1000000000));
    final Thread thread = new Thread(longComputationTask);
    thread.start();
    ThreadUtil.sleepMillis(100);
    thread.interrupt();
  }

  @AllArgsConstructor
  private static class LongComputationTask implements Runnable {

    private BigInteger base;

    private BigInteger power;

    @Override
    public void run() {
      log.info("{}^{}: {}", this.base, this.power, this.pow(this.base, this.power));
    }

    private BigInteger pow(final BigInteger base, final BigInteger power) {
      BigInteger result = BigInteger.ONE;
      for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
        if (Thread.currentThread().isInterrupted()) {
          log.error("Prematurely interrupted computation");
          return BigInteger.ZERO;
        }
        result = result.multiply(base);
      }
      return result;
    }
  }
}
