package com.functional.style.functionainterfaces;

import com.functional.style.data.Activities;
import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import java.util.List;
import java.util.function.BiConsumer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec02BiConsumerExample {

  public static void main(final String[] args) {
    final BiConsumer<String, String> biConsumer = (a, b) -> log.info("{}, {}", a, b);
    biConsumer.accept("java7", "java8");

    final BiConsumer<Integer, Integer> multiply = (a, b) -> log.info("Multiplication: {}", a * b);
    final BiConsumer<Integer, Integer> division = (a, b) -> log.info("Division: {}", a / b);

    multiply.andThen(division).accept(10, 5);

    nameAndActivities();

  }

  private static void nameAndActivities() {
    final BiConsumer<String, List<Activities>> biConsumer = (name, activities) -> log.info(
        "{}: Activities:{}", name, activities.toString());
    final List<Student> studentList = StudentDataBase.getAllStudents();
    studentList.forEach(
        student -> biConsumer.accept(student.getName(), student.getActivities())
    );

  }
}
