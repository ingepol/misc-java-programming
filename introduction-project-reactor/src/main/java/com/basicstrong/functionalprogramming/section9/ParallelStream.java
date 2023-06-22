package com.basicstrong.functionalprogramming.section9;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ParallelStream {

  public static void main(String[] args) {
    long startTime;
    long endTime;
    
    List<Employee> list = new ArrayList<>();
    for (int i = 0; i <= 100; i++) {
      list.add(Employee.builder().name("Paul").salary(20000).build());
      list.add(Employee.builder().name("Jhon").salary(3000).build());
      list.add(Employee.builder().name("Tom").salary(15000).build());
      list.add(Employee.builder().name("Bheem").salary(8000).build());
      list.add(Employee.builder().name("Shiva").salary(200).build());
      list.add(Employee.builder().name("Krishna").salary(50000).build());
    }

    startTime = System.currentTimeMillis();
    List<String> collect = list.stream()
        .filter(e -> e.getSalary() > 1000)
        .map(Employee::getName)
        .collect(Collectors.toList());
    log.info("Performing Sequentially " + collect);
    endTime = System.currentTimeMillis();
    log.info("Time taken with Sequential " + (endTime - startTime));

    startTime = System.currentTimeMillis();
    collect = list.parallelStream()
        .filter(e -> e.getSalary() > 1000)
        .map(Employee::getName)
        .collect(Collectors.toList());
    log.info("Performing parallel " + collect);
    endTime = System.currentTimeMillis();
    log.info("Time taken with parallel " + (endTime - startTime));

  }
}
