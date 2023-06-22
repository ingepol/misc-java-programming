package com.functional.style.streams;

import com.functional.style.data.Activities;
import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public class Lec01Example {

  public static void main(final String[] args) {

    //student name and there activities in a map
    final Predicate<Student> studentPredicate = student -> student.getGradeLevel() >= 3;
    final Predicate<Student> studentGpaPredicate = student -> student.getGpa() >= 3.9;

    final Map<String, List<Activities>> studentMap = StudentDataBase.getAllStudents()
        .stream()
        .peek(student -> log.info(student.toString()))
        .filter(studentPredicate)
        .peek(student -> log.info(student.toString()))
        .filter(studentGpaPredicate)
        .peek(student -> log.info(student.toString()))
        .collect(Collectors.toMap(Student::getName, Student::getActivities));
    log.info(studentMap.toString());

  }

}
