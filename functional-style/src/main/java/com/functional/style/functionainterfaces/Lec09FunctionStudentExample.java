package com.functional.style.functionainterfaces;

import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec09FunctionStudentExample {

  static Function<List<Student>, Map<String, Double>> studentFunction = students -> {
    Map<String, Double> studentGrade = new HashMap<>();
    students.forEach(student -> {
      if (Lec04PredicateStudentExample.p1.test(student)) {
        studentGrade.put(student.getName(), student.getGpa());
      }
    });
    return studentGrade;
  };

  public static void main(final String[] args) {
    log.info(studentFunction.apply(StudentDataBase.getAllStudents()).toString());
  }
}
