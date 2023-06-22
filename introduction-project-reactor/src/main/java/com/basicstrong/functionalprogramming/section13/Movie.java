package com.basicstrong.functionalprogramming.section13;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Movie {

  private String name;
  private int releaseYear;
  private String industry;
}
