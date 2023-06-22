package com.basicstrong.functionalprogramming.section7.designpatterns;

import com.basicstrong.functionalprogramming.section7.MyArrayList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IteratorPattern {

  public static void main(String[] args) {
    /*
      Accessing elements without exposing internal structure.
      Enables to get a way to access the elements of a collections object in sequential 
      manner without any need to know its internal representation 
      <java.util.Iterator> 
      <java.util.Enumeration> 
     */

    MyArrayList list = new MyArrayList(new Object[]{1, 3, 5, 67, 8});
    list.forEach(n -> log.info(String.valueOf(n)));
  }
}
