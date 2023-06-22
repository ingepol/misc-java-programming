package com.functional.style.streams;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.stream.Stream;

@Slf4j
public class Lec02CollectionsVsStreams {
  public static void main(final String[] args) {
    final ArrayList<String> names = new ArrayList<>();
    names.add("adam");
    names.add("jim");
    names.add("jenny");

    for (final String name : names) {
      log.info(name);
    }

    for (final String name : names) {
      log.info(name);
    }

    names.remove(0);
    log.info(names.toString());

    final Stream<String> nameStream = names.stream();
    nameStream.forEach(log::info);
  }
}
