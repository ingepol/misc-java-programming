package com.functional.style.functionainterfaces;

import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec10BiFunctionExample {

  static BiFunction<List<Student>, Predicate<Student>, Map<String, Double>> biFunction =
      (students, studentPredicate) -> {
        Map<String, Double> studentGrade = new HashMap<>();
        students.forEach(student -> {
          if (studentPredicate.test(student)) {
            studentGrade.put(student.getName(), student.getGpa());
          }
        });
        return studentGrade;
      };

  public static void main(final String[] args) {
    final Map<String, Double> studentsGrade = biFunction.apply(StudentDataBase.getAllStudents(),
        Lec04PredicateStudentExample.p1);
    log.info(studentsGrade.toString());
  }
}
