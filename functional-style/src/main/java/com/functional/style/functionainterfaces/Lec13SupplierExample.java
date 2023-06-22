package com.functional.style.functionainterfaces;

import static com.functional.style.data.Activities.BASKETBALL;
import static com.functional.style.data.Activities.SWIMMING;
import static com.functional.style.data.Activities.VOLLEYBALL;

import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec13SupplierExample {

  public static void main(final String[] args) {
    final Supplier<Student> supplier = () -> StudentDataBase.buildStudent(
        Arrays.asList(SWIMMING, BASKETBALL, VOLLEYBALL));
    final Supplier<List<Student>> listSupplier = () -> StudentDataBase.getAllStudents();
    log.info("Student is: {}", supplier.get());
    log.info("Students are: {}", listSupplier.get());
  }
}
