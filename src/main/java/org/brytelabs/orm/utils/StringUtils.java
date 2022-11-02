package org.brytelabs.orm.utils;

import java.util.Objects;

public class StringUtils {

  public static String quoteSqlValue(Object value) {
    if (value instanceof String st) {
      return "'" + st + "'";
    }

    return String.valueOf(value);
  }

  public static String capitalizeFirstLetter(String word) {
    Objects.requireNonNull(word);
    word = word.trim();
    if (word.isEmpty()) {
      throw new IllegalArgumentException("Cannot capitalize empty string");
    }
    return (word.charAt(0) + "").toUpperCase() + word.substring(1);
  }
}
