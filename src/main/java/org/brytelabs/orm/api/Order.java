package org.brytelabs.orm.api;

public record Order(Field field, Direction direction) {
  public static Order asc(String field) {
    return new Order(Field.with(field), Direction.ASC);
  }

  public static Order desc(String field) {
    return new Order(Field.with(field), Direction.DESC);
  }

  public static Order asc(Field field) {
    return new Order(field, Direction.ASC);
  }

  public static Order desc(Field field) {
    return new Order(field, Direction.DESC);
  }
}
