package com.basicstrong.functionalprogramming.section11;

import com.basicstrong.functionalprogramming.section9.FlatMapOperation;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Slf4j
public class DownStreamCollectors {

  public static void main(String[] args) {

    String resource = Objects.requireNonNull(
        FlatMapOperation.class.getResource("/texts/EmployeeData.txt")).getPath();

    Path p = Paths.get(resource);

    try (Stream<String> lines = Files.lines(p)) {
      Spliterator<String> spliterator = lines.flatMap(line -> Arrays.stream(line.split(",")))
          .spliterator();
      Spliterator<Employee> employeeSpliterator = new EmployeeSpliterator(spliterator);
      List<Employee> employeeList = StreamSupport.stream(employeeSpliterator, false)
          .collect(Collectors.toList());

      Map<String, Long> countByDestination = employeeList.stream()
          .collect(Collectors.groupingBy(Employee::getDesignation, Collectors.counting()));

      log.info(countByDestination.toString());

      Map<String, Double> funDistribution = employeeList.stream()
          .collect(Collectors.groupingBy(Employee::getDesignation, Collectors.summingDouble(
              Employee::getSalary
          )));
      log.info(funDistribution.toString());

      Map<String, Optional<Employee>> maxSalaryEmployees = employeeList.stream()
          .collect(Collectors.groupingBy(Employee::getDesignation,
              Collectors.maxBy(Comparator.comparing(Employee::getSalary)
              )));
      log.info(maxSalaryEmployees.toString());

      Map<String, Optional<Double>> maxSalaries = employeeList.stream()
          .collect(Collectors.groupingBy(Employee::getDesignation,
              Collectors.mapping(Employee::getSalary,
                  Collectors.maxBy(Comparator.comparing(Function.identity())))
          ));
      log.info(maxSalaries.toString());
    } catch (IOException ex) {
      log.error(ex.getMessage());
    }
  }
}
