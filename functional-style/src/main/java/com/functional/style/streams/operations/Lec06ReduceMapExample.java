package com.functional.style.streams.operations;

import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec06ReduceMapExample {

    private static int noOfNOteBooks() {
        return StudentDataBase.getAllStudents()
                .stream()
                .filter(student -> student.getGradeLevel() >= 3)
                .filter(student -> student.getGender().equals("female"))
                .map(Student::getNoteBooks)
                .reduce(0, Integer::sum);
    }

    public static void main(final String[] args) {
        log.info("notOfNoteBooks: {}", noOfNOteBooks());
    }
}
