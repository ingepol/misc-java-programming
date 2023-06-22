package com.functional.style.streams.operations;

import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Slf4j
public class Lec01MapExample {

    public static List<String> nameList() {
        return StudentDataBase.getAllStudents().stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .collect(toList());
    }

    public static Set<String> nameSet() {
        return StudentDataBase.getAllStudents().stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .collect(toSet());
    }

    public static void main(final String[] args) {
        log.info(nameList().toString());
        log.info(nameSet().toString());
    }

}
