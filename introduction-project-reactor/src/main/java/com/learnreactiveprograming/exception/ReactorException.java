package com.learnreactiveprograming.exception;

public class ReactorException extends Throwable {

  private final Throwable throwable;
  private final String message;

  public ReactorException(final Throwable cause, final String message) {
    this.throwable = cause;
    this.message = message;
  }
}
