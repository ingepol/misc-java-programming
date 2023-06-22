package com.functional.style.streams.operations;

import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;

@Slf4j
public class Lec03ComparatorExample {

    public static List<Student> sortStudentByName() {
        return StudentDataBase.getAllStudents().stream()
                .sorted(Comparator.comparing(Student::getName))
                .toList();
    }

    public static List<Student> sortStudentByGpa() {
        return StudentDataBase.getAllStudents().stream()
                .sorted(Comparator.comparing(Student::getGpa))
                .toList();
    }

    public static List<Student> sortStudentByGpaDesc() {
        return StudentDataBase.getAllStudents().stream()
                .sorted(Comparator.comparing(Student::getGpa).reversed())
                .toList();
    }

    public static void main(final String[] args) {
        log.info("sortStudent By Name");
        sortStudentByName().forEach(student -> log.info(student.toString()));
        log.info("sortStudent By Gpa");
        sortStudentByGpa().forEach(student -> log.info(student.toString()));
        log.info("sortStudentBy Gpa Desc");
        sortStudentByGpaDesc().forEach(student -> log.info(student.toString()));
    }
}
