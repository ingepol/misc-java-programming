package com.functional.style.streamsterminal;

import com.functional.style.data.StudentDataBase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec02StreamsCountingExample {

    private static long count() {
        return StudentDataBase.getAllStudents()
                .stream()
                .filter(student -> student.getGpa() >= 3.9)
                .count();
    }

    public static void main(final String[] args) {
        log.info("Counting :" + count());
    }
}
