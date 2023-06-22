package com.basicstrong.functionalprogramming.section11;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Employee implements Comparable<Employee> {

  private int id;
  private String name;
  private char gender;
  private Date dob;
  private String city;
  private String designation;
  private Date joiningDate;
  private double salary;

  @Override
  public int compareTo(Employee o) {
    return Integer.compare(this.id, o.id);
  }
}
