package com.basicstrong.functionalprogramming.section7;

import lombok.AllArgsConstructor;

import java.util.function.Consumer;

@AllArgsConstructor
public class MyArrayList {

  Object[] elements;

  public void forEach(Consumer<Object> action) {
    for (Object element : elements) {
      action.accept(element);
    }
  }
}
