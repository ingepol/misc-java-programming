package com.basicstrong.functionalprogramming.section13;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Slf4j
public class MapFunctionalOperations {

  private static final String SEPARATOR_LINE = "-----------------";

  public static void main(String[] args) {
    Map<String, String> contacts = new HashMap<>();
    contacts.put("1654641", "Jhon");
    contacts.put("2132131", "Jhon");
    contacts.put("1454212", "Neal");
    contacts.put("2132134", "Raju");
    contacts.put("1676342", "Peter");
    contacts.put("9876543", "Neha");

    //Traversal
    for (Entry<String, String> entry : contacts.entrySet()) {
      log.info(entry.getKey() + " - " + entry.getValue());
    }
    log.info(SEPARATOR_LINE);
    contacts.forEach((k, v) -> log.info(k + " - " + v));

    //Filter
    Map<String, String> filteredContacts = contacts.entrySet().stream()
        .filter(contact -> "Jhon".equalsIgnoreCase(contact.getValue()))
        .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    log.info(filteredContacts.toString());

    //Map
    String contactNames = contacts.entrySet().stream()
        .distinct()
        .map(Entry::getValue)
        .collect(Collectors.joining(", "));
    log.info(contactNames);

    //sorting
    LinkedHashMap<String, String> sortedMap = contacts.entrySet().stream()
        .sorted(Entry.comparingByValue())
        .collect(
            Collectors.toMap(Entry::getKey, Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
    sortedMap.forEach((k, v) -> log.info(k + " - " + v));

    //Reduce
    Map<String, Double> marks = new HashMap<>();
    marks.put("Science", 66.0);
    marks.put("Maths", 78.0);
    marks.put("English", 90.0);

    double average = marks.values().stream()
        .mapToDouble(m -> m)
        .average().orElse(0.0);
    log.info(String.valueOf(average));

  }
}
