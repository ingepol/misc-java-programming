package com.basicstrong.functionalprogramming.section11;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Spliterator;
import java.util.function.Consumer;

public class EmployeeSpliterator implements Spliterator<Employee> {

  private Spliterator<String> wordsSpliterator;

  private int id;
  private String name;
  private char gender;
  private Date dob;
  private String city;
  private String designation;
  private Date joiningDate;
  private double salary;

  public EmployeeSpliterator(Spliterator<String> wordsSpliterator) {
    this.wordsSpliterator = wordsSpliterator;
  }

  @Override
  public boolean tryAdvance(Consumer<? super Employee> consumer) {
    if (this.wordsSpliterator.tryAdvance(word -> this.id = Integer.parseInt(word)) &&
        this.wordsSpliterator.tryAdvance(word -> this.name = word) &&
        this.wordsSpliterator.tryAdvance(word -> this.gender = word.charAt(0)) &&
        this.wordsSpliterator.tryAdvance(word -> this.dob = getDate(word)) &&
        this.wordsSpliterator.tryAdvance(word -> this.city = word) &&
        this.wordsSpliterator.tryAdvance(word -> this.designation = word) &&
        this.wordsSpliterator.tryAdvance(word -> this.joiningDate = getDate(word)) &&
        this.wordsSpliterator.tryAdvance(word -> this.salary = Double.parseDouble(word))) {
      consumer.accept(Employee.builder()
          .id(this.id)
          .name(this.name)
          .gender(this.gender)
          .dob(this.dob)
          .city(this.city)
          .designation(this.designation)
          .joiningDate(this.joiningDate)
          .salary(this.salary)
          .build());
      return true;
    }
    return false;
  }

  @Override
  public Spliterator<Employee> trySplit() {
    return null;
  }

  @Override
  public long estimateSize() {
    return wordsSpliterator.estimateSize() / 8;
  }

  @Override
  public int characteristics() {
    return wordsSpliterator.characteristics();
  }

  private Date getDate(String word) {
    try {
      return DateFormat.getInstance().parse(word);
    } catch (ParseException e) {
      return new Date();
    }
  }
}
