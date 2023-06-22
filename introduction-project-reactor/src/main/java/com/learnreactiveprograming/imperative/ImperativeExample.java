package com.learnreactiveprograming.imperative;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ImperativeExample {

  public static void main(final String[] args) {
    final List<String> nameList = List.of("alex", "ben", "chloe", "adam", "adam");
    final List<String> newNameList = namesGreaterThanSize(nameList, 3);
    log.info(newNameList.toString());
  }

  private static List<String> namesGreaterThanSize(final List<String> nameList, final int size) {
    final var newNamesList = new ArrayList<String>();
    for (final String name : nameList) {
      if (name.length() > size && !newNamesList.contains(name)) {
        newNamesList.add(name.toUpperCase());
      }
    }
    return newNamesList;
  }

}
