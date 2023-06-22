package com.functional.style.streams.operations;

import com.functional.style.data.Activities;
import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Lec02FlatMapExample {

    public static List<Activities> getStudentActivities() {
        return StudentDataBase.getAllStudents().stream()
                .map(Student::getActivities)
                .flatMap(List::stream)
                .distinct()
                .sorted()
                .toList();
    }

    public static long getStudentActivitiesCount() {
        return StudentDataBase.getAllStudents().stream()
                .map(Student::getActivities)
                .flatMap(List::stream)
                .distinct()
                .count();
    }

    public static void main(final String[] args) {
        log.info("Activities: {}", getStudentActivities());
        log.info("Count: {}", getStudentActivitiesCount());
    }
}
