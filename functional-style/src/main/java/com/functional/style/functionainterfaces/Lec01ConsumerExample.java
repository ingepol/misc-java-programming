package com.functional.style.functionainterfaces;

import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import java.util.List;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec01ConsumerExample {

  static final Consumer<Student> c2 = student -> log.info(student.toString());
  static final Consumer<Student> c3 = student -> log.info(student.getName());
  static final Consumer<Student> c4 = student -> log.info(student.getActivities().toString());

  public static void main(final String[] args) {
    final Consumer<String> c1 = s -> log.info(s.toUpperCase());
    c1.accept("java8");
    log.info("=======printStudents=======");
    printStudents();
    log.info("=======printNameAndActivities=======");
    printNameAndActivities();
    log.info("=======printNameAndActivitiesUsingCondition=======");
    printNameAndActivitiesUsingCondition();
  }

  private static void printStudents() {
    final List<Student> studentList = StudentDataBase.getAllStudents();
    studentList.forEach(c2);
  }

  private static void printNameAndActivities() {
    final List<Student> studentList = StudentDataBase.getAllStudents();
    studentList.forEach(c3.andThen(c4));
  }

  private static void printNameAndActivitiesUsingCondition() {
    final List<Student> studentList = StudentDataBase.getAllStudents();
    studentList.forEach(student -> {
      if (student.getGradeLevel() >= 3 && student.getGpa() >= 3.9) {
        c2.andThen(c3).andThen(c4).accept(student);
      }
    });
  }

}
