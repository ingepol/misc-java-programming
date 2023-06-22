package com.basicstrong.functionalprogramming.section8.designpattern;

import com.basicstrong.functionalprogramming.section8.floor.FlooringFactory;
import com.basicstrong.functionalprogramming.section8.interfaces.Flooring;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FactoryDemo {

  public static void main(String[] args) {
    /*
    Creating object without exposing instantiation logic.
    e.g. Calendar.getInstance()
    Is a way to instantiating a class inside a designated method that we call a factory method.
    `Factory is an object that is able to create other object.
     */

    Flooring floor = FlooringFactory.getFlooring(-1, 18);
    floor.installation();
  }
}
