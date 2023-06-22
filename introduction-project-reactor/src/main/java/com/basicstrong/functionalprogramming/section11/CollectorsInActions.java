package com.basicstrong.functionalprogramming.section11;

import com.basicstrong.functionalprogramming.section9.FlatMapOperation;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Slf4j
public class CollectorsInActions {

  public static void main(String[] args) {
    String resource = Objects.requireNonNull(
        FlatMapOperation.class.getResource("/texts/EmployeeData.txt")).getPath();

    Path p = Paths.get(resource);

    try (Stream<String> lines = Files.lines(p)) {
      Stream<String> word = lines.flatMap(line -> Arrays.stream(line.split(",")));
      Spliterator<String> spliterator = word.spliterator();
      Spliterator<Employee> employeeSpliterator = new EmployeeSpliterator(spliterator);
      Stream<Employee> employees = StreamSupport.stream(employeeSpliterator, false);

      List<Employee> employeeList = employees.collect(Collectors.toList());

      List<String> employeeNames = employeeList.stream()
          .map(Employee::getName)
          .collect(Collectors.toList());

      employeeNames.forEach(log::info);
      log.info("------------X-------LIST------------X--------------");

      Set<String> destinations = employeeList.stream()
          .map(Employee::getDesignation)
          .collect(Collectors.toSet());
      destinations.forEach(log::info);
      log.info("------------X-------SET------------X--------------");

      //TreeSet<Employee> employeeSorted = employeeList.stream()
      //    .collect(Collectors.toCollection(TreeSet::new));
      TreeSet<Employee> employeeSorted = new TreeSet<>(employeeList);
      employeeSorted.forEach(emp -> log.info(emp.toString()));
      log.info("------------X-------Collection[TreeSet]------------X--------------");

      Map<Integer, String> getNameById = employeeList.stream()
          .collect(Collectors.toMap(Employee::getId, Employee::getName));
      log.info(getNameById.toString());
      log.info("------------X-------Map Name by Id------------X--------------");

      Map<Boolean, List<Employee>> partitionData = employeeList.stream()
          .collect(Collectors.partitioningBy(e -> e.getGender() == 'M'));
      partitionData.get(true).forEach(e -> log.info(e.toString()));
      log.info("------------X-------Male Employees------------X--------------");
      partitionData.get(false).forEach(e -> log.info(e.toString()));
      log.info("------------X-------Female Employees------------X--------------");

      Map<String, List<Employee>> getByDestination = employeeList.stream()
          .collect(Collectors.groupingBy(Employee::getDesignation));
      log.info(getByDestination.toString());
      log.info("------------X-------Map Grouping by Destination------------X--------------");

      String employeeNamesString = employeeList.stream()
          .map(Employee::getName)
          .collect(Collectors.joining(", "));
      log.info(employeeNamesString);

    } catch (IOException e) {
      log.error(e.getMessage());
    }
  }
}
