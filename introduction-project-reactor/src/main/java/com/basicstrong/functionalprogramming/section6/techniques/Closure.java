package com.basicstrong.functionalprogramming.section6.techniques;

import com.basicstrong.functionalprogramming.section6.interfaces.Task;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Closure {

  public static void main(String[] args) {
    // A closure is a function that refers to free variables in its lexical context
    // function: block of code it may produce a result
    // free variable: is an identifier used but not defined by the closure 
    // lexical context (lexical scope): It is a convention that sets the scope of a variable 
    // so that may only be called from within the block of code which it is defined
    int val = 10;
    Task lambda = () -> {
      // val = 22;
      log.info(String.valueOf(val));
      log.info("Task completed!");
    };

    printLambda(lambda);
  }

  private static void printLambda(Task lambda) {
    lambda.doTask();
  }
}
