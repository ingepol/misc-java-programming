package com.basicstrong.functionalprogramming.section14;

import lombok.Data;

@Data
public class Entry {

  Object key;
  Object value;
  Entry next;

  public Entry(Object key, Object value) {
    this.key = key;
    this.value = value;
    this.next = null;
  }

  public Entry() {
    this.next = null;
  }

  @Override
  public String toString() {
    return "key:" + key + " value:" + value + " next= " + next;
  }

}
