package com.functional.style.streamsterminal;

import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec05StreamsSumAvgExample {

    public static int sum() {
        return StudentDataBase.getAllStudents()
                .stream().mapToInt(Student::getNoteBooks).sum();
    }

    public static Double average() {
        return StudentDataBase.getAllStudents()
                .stream()
                .mapToDouble(Student::getNoteBooks)
                .average()
                .orElse(0);
    }

    public static void main(final String[] args) {
        log.info("Total No of Notebooks: " + sum());
        log.info("Average No of Notebooks: " + average());
    }
}
