package com.functional.style.streams.operations;

import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
public class Lec05ReduceExample {

    public static int performMultiplication(final List<Integer> integerList) {
        return integerList.stream()
                //1
                //3
                //5
                //7
                // a=1,b=1(from stream) => result 1 is returned
                // a=1,b=3(from stream) => result 3 is returned
                // a=3,b=5(from stream) => result 15 is returned
                // a=15,b=7(from stream) => result 105 is returned
                .reduce(1, (a, b) -> a * b);
    }

    public static Optional<Integer> performMultiplicationWithoutIdentity(final List<Integer> integerList) {
        return integerList.stream()
                .reduce((a, b) -> a * b);
    }

    public static Optional<Student> getHighestGPAStudent() {
        return StudentDataBase.getAllStudents()
                .stream()
                .reduce((s1, s2) -> s1.getGpa() > s2.getGpa() ? s1 : s2);
    }

    public static void main(final String[] args) {
        final List<Integer> integerList = Arrays.asList(1, 3, 5, 7);
        final List<Integer> integerListEmpty = new ArrayList<>();
        log.info("Result is: {}", performMultiplication(integerList));

        final Optional<Integer> integer = performMultiplicationWithoutIdentity(integerList);
        log.info("Result is Present: {}", integer.isPresent());
        log.info("Result is: {}", integer.orElse(0));

        final Optional<Integer> integerEmpty = performMultiplicationWithoutIdentity(integerListEmpty);
        log.info("Result is Present: {}", integerEmpty.isPresent());

        final Optional<Student> studentOptional = getHighestGPAStudent();
        studentOptional.ifPresent(student -> log.info(student.toString()));
    }
}
