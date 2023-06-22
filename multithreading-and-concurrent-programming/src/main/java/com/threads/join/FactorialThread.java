package com.threads.join;

import java.math.BigInteger;
import lombok.Getter;

public class FactorialThread extends Thread {

  private final long inputNumber;
  @Getter
  private BigInteger result = BigInteger.ZERO;
  @Getter
  private boolean isFinished = false;

  public FactorialThread(long inputNumber) {
    this.inputNumber = inputNumber;
  }

  @Override
  public void run() {
    this.result = factorial(inputNumber);
    this.isFinished = true;
  }

  private BigInteger factorial(long n) {
    BigInteger tmpResult = BigInteger.ONE;
    for (long i = n; i > 0; i--) {
      tmpResult = tmpResult.multiply(BigInteger.valueOf(i));
    }
    return tmpResult;
  }
}
