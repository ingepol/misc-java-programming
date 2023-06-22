package com.functional.style.methodreference;

import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec03RefactorExample {

  static Predicate<Student> p1 = Lec03RefactorExample::greaterThanGradeLevel;

  private static boolean greaterThanGradeLevel(final Student student) {
    return student.getGradeLevel() >= 3;
  }


  public static void main(final String[] args) {
    final boolean test = p1.test(StudentDataBase.studentSupplier.get());
    log.info(String.valueOf(test));
  }
}
