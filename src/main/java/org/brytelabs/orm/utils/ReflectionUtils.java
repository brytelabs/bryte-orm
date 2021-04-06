package org.brytelabs.orm.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReflectionUtils {
  public static List<Field> getDeclaredFields(Class<?> type) {
    Class<?> temp = type;
    final List<Field> fields = new ArrayList<>();
    while (temp != Object.class) {
      fields.addAll(Arrays.asList(temp.getDeclaredFields()));
      temp = temp.getSuperclass();
    }
    return fields;
  }

  public static boolean hasDefaultConstructor(Class<?> type) {
    return Arrays.stream(type.getDeclaredConstructors()).anyMatch(c -> c.getParameterCount() == 0);
  }

  public static Constructor<?> getAllArgsConstructorOrDefault() {
    return null;
  }

  public static Map<String, Field> getDeclaredFieldsMap(Class<?> type) {
    return getDeclaredFields(type).stream()
        .collect(Collectors.toMap(f -> f.getName().toLowerCase().replaceAll("-", ""), f -> f));
  }
}
