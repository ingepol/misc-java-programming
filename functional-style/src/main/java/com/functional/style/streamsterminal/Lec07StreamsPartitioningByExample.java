package com.functional.style.streamsterminal;

import com.functional.style.data.Student;
import com.functional.style.data.StudentDataBase;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toSet;

@Slf4j
public class Lec07StreamsPartitioningByExample {

	public static void partitioningBy1() {

		Predicate<Student> gpaPredicate = student -> student.getGpa() >= 3.8;

		Map<Boolean, List<Student>> partitioningMap = StudentDataBase.getAllStudents().stream()
				.collect(partitioningBy(gpaPredicate));
		log.info("partitioningMap" + partitioningMap);
	}

	public static void partitioningBy2() {

		Predicate<Student> gpaPredicate = student -> student.getGpa() >= 3.8;

		Map<Boolean, Set<Student>> partitioningSet = StudentDataBase.getAllStudents().stream()
				.collect(partitioningBy(gpaPredicate, toSet()));
		log.info("partitioningSet" + partitioningSet);
	}

	public static void main(String[] args) {
		partitioningBy1();
		partitioningBy2();
	}
}
