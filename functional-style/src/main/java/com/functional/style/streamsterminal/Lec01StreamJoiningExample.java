package com.functional.style.streamsterminal;

import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

@Slf4j
public class Lec01StreamJoiningExample {

    private static String joining_1() {
        return StudentDataBase.getAllStudents()
                .stream()
                .map(Student::getName)
                .collect(Collectors.joining());
    }

    private static String joining_2() {
        return StudentDataBase.getAllStudents()
                .stream()
                .map(Student::getName)
                .collect(Collectors.joining("-"));
    }

    private static String joining_3() {
        return StudentDataBase.getAllStudents()
                .stream()
                .map(Student::getName)
                .collect(Collectors.joining("-", "(", ")"));
    }


    public static void main(final String[] args) {
        log.info("joining_1 : " + joining_1());
        log.info("joining_2 : " + joining_2());
        log.info("joining_3 : " + joining_3());
    }
}
