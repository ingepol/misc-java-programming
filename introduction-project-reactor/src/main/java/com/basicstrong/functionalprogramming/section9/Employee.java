package com.basicstrong.functionalprogramming.section9;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Employee {

  private String name;
  private int salary;

}
