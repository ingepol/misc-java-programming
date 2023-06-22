package com.functional.style.functionainterfaces;

import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import java.util.List;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec04PredicateStudentExample {

  static final Predicate<Student> p1 = student -> student.getGradeLevel() >= 3;
  static final Predicate<Student> p2 = student -> student.getGpa() >= 3.9;

  public static void main(final String[] args) {
    final List<Student> studentList = StudentDataBase.getAllStudents();

    filterStudentByGradeLevel(studentList);
    filterStudentByGpa(studentList);
    filterStudent(studentList);
  }

  private static void filterStudentByGradeLevel(final List<Student> studentList) {
    log.info("===filterStudentByGradeLevel===");
    studentList.forEach(student -> {
      if (p1.test(student)) {
        log.info(student.toString());
      }
    });
  }

  private static void filterStudentByGpa(final List<Student> studentList) {
    log.info("===filterStudentByGpa===");
    studentList.forEach(student -> {
      if (p2.test(student)) {
        log.info(student.toString());
      }
    });
  }

  private static void filterStudent(final List<Student> studentList) {
    log.info("===filterStudent===");
    studentList.forEach(student -> {
      if (p1.and(p2).test(student)) {
        log.info(student.toString());
      }
    });
  }
}
