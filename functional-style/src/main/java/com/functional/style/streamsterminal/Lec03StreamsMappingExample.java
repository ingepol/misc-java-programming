package com.functional.style.streamsterminal;

import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class Lec03StreamsMappingExample {

    public static void main(final String[] args) {
        final List<String> nameList = StudentDataBase.getAllStudents()
                .stream().map(Student::getName).toList();

        log.info("nameList : " + nameList);

        final Set<String> nameSet = StudentDataBase.getAllStudents()
                .stream().map(Student::getName).collect(Collectors.toSet());

        log.info("nameSet : " + nameSet);
    }
}
