package com.functional.style.streamsterminal;

import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

@Slf4j
public class Lec06StreamsGroupingByExample {

	public static void groupStudentsByGender() {
		final Map<String, List<Student>> studentMap = StudentDataBase.getAllStudents().stream()
				.collect(groupingBy(Student::getGender));
		log.info(studentMap.toString());
	}

	public static void customizedGroupingBy() {
		final Map<String, List<Student>> studentMap = StudentDataBase.getAllStudents().stream()
				.collect(groupingBy(student -> student.getGpa() > 3.8 ? "OUTSTANDING" : "AVERAGE"));
		log.info(studentMap.toString());
	}

	public static void twoLevelGrouping1() {
		final Map<Integer, Map<String, List<Student>>> studentMap = StudentDataBase.getAllStudents().stream()
				.collect(groupingBy(Student::getGradeLevel,
						groupingBy(student -> student.getGpa() > 3.8 ? "OUTSTANDING" : "AVERAGE")));
		log.info(studentMap.toString());
	}

	public static void twoLevelGrouping2() {
		final Map<Integer, Integer> studentMap = StudentDataBase.getAllStudents().stream()
				.collect(groupingBy(Student::getGradeLevel, summingInt(Student::getNoteBooks)));
		log.info(studentMap.toString());
	}

	public static void threeArgumentGroupBy() {
		final LinkedHashMap<String, Set<Student>> studentLinked = StudentDataBase.getAllStudents().stream()
				.collect(groupingBy(Student::getName, LinkedHashMap::new, toSet()));

		log.info(studentLinked.toString());
	}

	public static void calculateTopGpa() {
		final List<Student> allStudents = StudentDataBase.getAllStudents();
		final Map<Integer, Optional<Student>> studentMapOptional = allStudents.stream()
				.collect(groupingBy(Student::getGradeLevel, maxBy(Comparator.comparing(Student::getGpa))));

		log.info(studentMapOptional.toString());

		final Map<Integer, Student> studentMapOptional1 = allStudents.stream().collect(toMap(Student::getGradeLevel,
				Function.identity(), BinaryOperator.maxBy(Comparator.comparing(Student::getGpa))));

		log.info(studentMapOptional1.toString());
	}

	public static void calculateLeastGpa() {
		final List<Student> allStudents = StudentDataBase.getAllStudents();
		final Map<Integer, Optional<Student>> studentMapOptional = allStudents.stream()
				.collect(groupingBy(Student::getGradeLevel, minBy(Comparator.comparing(Student::getGpa))));

		log.info(studentMapOptional.toString());

		final Map<Integer, Student> studentMapOptional1 = allStudents.stream().collect(toMap(Student::getGradeLevel,
				Function.identity(), BinaryOperator.minBy(Comparator.comparing(Student::getGpa))));

		log.info(studentMapOptional1.toString());
	}

	public static void main(final String[] args) {
		groupStudentsByGender();
		customizedGroupingBy();
		twoLevelGrouping1();
		twoLevelGrouping2();
		threeArgumentGroupBy();
		calculateTopGpa();
		calculateLeastGpa();
	}
}
