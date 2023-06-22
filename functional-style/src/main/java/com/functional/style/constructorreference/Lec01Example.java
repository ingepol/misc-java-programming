package com.functional.style.constructorreference;

import com.functional.style.data.Student;
import java.util.function.Function;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec01Example {

  static Supplier<Student> studentSupplier = Student::new;
  static Function<String, Student> studentFunction = Student::new;

  public static void main(final String[] args) {
    log.info(studentSupplier.get().toString());
    log.info(studentFunction.apply("ABC").toString());
  }
}
