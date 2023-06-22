package com.functional.style.streams.operations;

import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Slf4j
public class Lec10FindAnyFirstExample {

    public static final Predicate<Student> STUDENT_PREDICATE = student -> student.getGpa() >= 3.9;

    public static Optional<Student> findAnyStudent(final List<Student> studentList) {
        return studentList.stream()
                .filter(STUDENT_PREDICATE)
                .findAny();
    }

    public static Optional<Student> findFirstStudent(final List<Student> studentList) {
        return studentList.stream()
                .filter(STUDENT_PREDICATE)
                .findFirst();
    }

    public static void main(final String[] args) {
        final List<Student> allStudents = StudentDataBase.getAllStudents();
        allStudents.forEach(student -> log.info(student.toString()));
        final Optional<Student> anyStudent = findAnyStudent(allStudents);
        anyStudent.ifPresent(student -> log.info("Found the student {}", anyStudent.get()));

        final Optional<Student> firstStudent = findFirstStudent(allStudents);
        anyStudent.ifPresent(student -> log.info("Found the student {}", firstStudent.get()));
    }
}
