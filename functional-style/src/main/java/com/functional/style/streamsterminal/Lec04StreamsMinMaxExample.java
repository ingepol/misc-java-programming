package com.functional.style.streamsterminal;


import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.Optional;

@Slf4j
public class Lec04StreamsMinMaxExample {

    public static Optional<Student> minByExample() {
        return StudentDataBase.getAllStudents()
                .stream()
                .min(Comparator.comparing(Student::getGpa));
    }

    public static Optional<Student> maxByExample() {
        return StudentDataBase.getAllStudents()
                .stream()
                .max(Comparator.comparing(Student::getGpa));
    }

    public static void main(final String[] args) {
        log.info(" minByExample: " + minByExample());
        log.info(" maxByExample: " + minByExample());
    }
}
