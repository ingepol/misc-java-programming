package com.functional.style.methodreference;

import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec02ConsumerReference {

  static Function<Student, String> f1 = Student::toString;
  static Consumer<String> c2 = log::info;

  public static void main(final String[] args) {
    final List<Student> allStudents = StudentDataBase.getAllStudents();
    allStudents.forEach(student -> c2.accept(f1.apply(student)));

  }
}
