package com.functional.style.streams.operations;

import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Lec09MatchExample {

    public static boolean allMatch(final List<Student> students) {
        return students.stream()
                //all students match with the predicate
                .allMatch(student -> student.getGpa() >= 3.9);
    }

    public static boolean anyMatch(final List<Student> students) {
        return students.stream()
                //any student match with the predicate
                .anyMatch(student -> student.getGpa() >= 4.0);
    }

    public static boolean noneMatch(final List<Student> students) {
        return students.stream()
                //Just opposite to allMatch(). 
                .noneMatch(student -> student.getGpa() >= 4.1);
    }

    public static void main(final String[] args) {
        final List<Student> allStudents = StudentDataBase.getAllStudents();
        allStudents.forEach(student -> log.info(student.toString()));
        log.info("Result of All Match: {}", allMatch(allStudents));
        log.info("Result of Any Match: {}", anyMatch(allStudents));
        log.info("Result of None Match: {}", noneMatch(allStudents));

    }
}
