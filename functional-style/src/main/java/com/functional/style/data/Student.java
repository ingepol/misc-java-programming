package com.functional.style.data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@ToString
@Builder
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Student {

  private String name;

  private int gradeLevel;

  private double gpa;

  private String gender;

  private List<Activities> activities;

  private int noteBooks;

  public Student(final String name) {
    this.name = name;
  }

}
