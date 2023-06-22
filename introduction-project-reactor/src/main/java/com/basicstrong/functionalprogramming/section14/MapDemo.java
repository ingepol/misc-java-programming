package com.basicstrong.functionalprogramming.section14;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapDemo {

  public static void main(String[] args) {
    MapFun<Integer, String> map = new MapFun<>(5);
    map.put(1, "Nikhil");
    map.put(2, "Sourabh");
    map.put(3, "Alex");
    map.put(5, "Shawn");
    map.put(100, "Justin");
    map.display();

  }
}
