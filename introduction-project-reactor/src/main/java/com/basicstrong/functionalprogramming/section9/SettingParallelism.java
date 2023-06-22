package com.basicstrong.functionalprogramming.section9;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

@Slf4j
public class SettingParallelism {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    log.info(String.valueOf(Runtime.getRuntime().availableProcessors()));

    System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "2");
    log.info(String.valueOf(ForkJoinPool.getCommonPoolParallelism()));

    ForkJoinPool pool = new ForkJoinPool(2);
    List<Employee> list = new ArrayList<>();
    for (int i = 0; i <= 100; i++) {
      list.add(Employee.builder().name("Paul").salary(20000).build());
      list.add(Employee.builder().name("Jhon").salary(3000).build());
      list.add(Employee.builder().name("Tom").salary(15000).build());
      list.add(Employee.builder().name("Bheem").salary(8000).build());
      list.add(Employee.builder().name("Shiva").salary(200).build());
      list.add(Employee.builder().name("Krishna").salary(50000).build());
    }

    Long count = pool.submit(() -> list.parallelStream()
        .filter(emp -> emp.getSalary() > 1000)
        .count()).get();

    log.info(count.toString());

    //Computational Intensive
    //Number of threads <= number of cores

    //IO intensive
    //Number of threads > number of cores

  }
}
