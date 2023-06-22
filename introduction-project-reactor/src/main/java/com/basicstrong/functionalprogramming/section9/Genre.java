package com.basicstrong.functionalprogramming.section9;

public enum Genre {
  HORROR("Horror"), ADVENTURE("Adventure"), ROMANCE("Romance"),
  FANTASY_FICTION("Fantasy Fiction"), SCIENCE_FICTION("Science Fiction"),
  Mystery("Mystery");

  private String name;

  private Genre(String stringVal) {
    name = stringVal;
  }

  public String toString() {
    return name;
  }

  public static Genre getEnumByString(String code) {
    for (Genre e : Genre.values()) {
      if (e.name.equals(code)) {
        return e;
      }
    }
    return null;
  }
}
