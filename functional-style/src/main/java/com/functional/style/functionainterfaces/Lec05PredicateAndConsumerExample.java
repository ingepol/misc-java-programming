package com.functional.style.functionainterfaces;

import com.functional.style.data.Activities;
import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec05PredicateAndConsumerExample {

  Predicate<Student> p1 = student -> student.getGradeLevel() >= 3;
  Predicate<Student> p2 = student -> student.getGpa() >= 3.9;

  BiConsumer<String, List<Activities>> studentBiConsumer = (name, activities) -> log.info(
      "{}: Activities{}", name, activities.toString());

  Consumer<Student> studentConsumer = student -> {
    if (p1.and(p2).test(student)) {
      studentBiConsumer.accept(student.getName(), student.getActivities());
    }
  };

  public void printNameStudentsAndActivities(final List<Student> studentList) {
    studentList.forEach(studentConsumer);
  }

  public static void main(final String[] args) {
    final List<Student> allStudents = StudentDataBase.getAllStudents();
    new Lec05PredicateAndConsumerExample().printNameStudentsAndActivities(allStudents);
  }
}
