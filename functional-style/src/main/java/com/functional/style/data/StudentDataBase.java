package com.functional.style.data;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static com.functional.style.data.Activities.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentDataBase {

    public static final Supplier<Student> studentSupplier = () ->
            buildStudent(Arrays.asList(SWIMMING, BASKETBALL, VOLLEYBALL));

    /**
     * Total of 6 students in the database
     *
     * @return List of {@link Student}
     */
    @SuppressWarnings("")

    public static List<Student> getAllStudents() {
        return Arrays.asList(
                buildStudent(Arrays.asList(SWIMMING, BASKETBALL, VOLLEYBALL)),
                buildStudent(Arrays.asList(SWIMMING, GYMNASTIC, SOCCER)),
                buildStudent(Arrays.asList(SWIMMING, GYMNASTIC, AEROBICS)),
                buildStudent(Arrays.asList(SWIMMING, GYMNASTIC, SOCCER)),
                buildStudent(Arrays.asList(SWIMMING, DANCING, FOOTBALL)),
                buildStudent(Arrays.asList(SWIMMING, BASKETBALL, BASEBALL, FOOTBALL))
        );
    }

    public static Student buildStudent(final List<Activities> activities) {
        final Faker faker = Faker.instance();
        return Student.builder()
                .name(faker.name().firstName())
                .gender(faker.dog().gender())
                .gpa(faker.number().randomDouble(1, 0, 5))
                .gradeLevel(faker.random().nextInt(2, 4))
                .activities(activities)
                .noteBooks(faker.random().nextInt(1, 20))
                .build();
    }
}
