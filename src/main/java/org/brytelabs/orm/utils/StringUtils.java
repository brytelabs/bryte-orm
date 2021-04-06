package org.brytelabs.orm.utils;

import java.util.Objects;
import java.util.function.Function;

public class StringUtils {

  public static String quoteSqlValue(Object value) {
    Function<String, String> format = (txt) -> String.format("'%s'", txt);

    if (value instanceof String) {
      return format.apply(String.valueOf(value));
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
