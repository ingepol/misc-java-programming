package com.functional.style.streams.operations;

import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
public class Lec04FilterExample {

    public static List<Student> filterStudents() {
        return StudentDataBase.getAllStudents().stream() //Stream<Student>
                .filter(student -> student.getGender().equals("female")) //Stream<Student>
                // filters and sends only the students whose gender is female
                .filter(student -> student.getGpa() > 3.9)
                .collect(toList());
    }

    public static void main(final String[] args) {
        log.info(filterStudents().toString());
    }
}
