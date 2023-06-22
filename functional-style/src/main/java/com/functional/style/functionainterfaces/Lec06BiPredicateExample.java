package com.functional.style.functionainterfaces;

import com.functional.style.data.Activities;
import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec06BiPredicateExample {

  BiPredicate<Integer, Double> biPredicate = (gradeLevel, gpa) -> gradeLevel >= 3 && gpa >= 3.9;

  BiConsumer<String, List<Activities>> studentBiConsumer = (name, activities) -> log.info(
      "{}: Activities{}", name, activities.toString());

  Consumer<Student> studentConsumer = student -> {
    if (biPredicate.test(student.getGradeLevel(), student.getGpa())) {
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
