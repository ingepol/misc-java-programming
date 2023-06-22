package com.basicstrong.functionalprogramming.section1;

import com.basicstrong.functionalprogramming.section2.interfaces.MyFunInterface;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LambdaUnderTheHood {

  public static void main(String[] args) {

    /*
      // This way when compile the lambda, create three LambdaUnderHood .class.  The main and 
      // one for each lambda implementation.
      MyFunInterface fn = new MyFunInterface() {
        @Override
        public void Method1() {
          log.info("Implementation one");
        }
      };
  
      MyFunInterface fn2 = new MyFunInterface() {
        @Override
        public void Method1() {
          log.info("Implementation two");
        }
      };
    */

    // Type Inference and Invoke dynamic
    MyFunInterface fn = () -> log.info("I am light weight");
    fn.Method1();

  }

}
