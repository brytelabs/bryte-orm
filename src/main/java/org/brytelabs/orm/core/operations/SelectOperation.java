package org.brytelabs.orm.core.operations;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;

public enum SelectOperation {
  ALL("*"),
  FIELDS(""),
  COUNT("count"),
  SUM("sum"),
  AVG("avg"),
  MAX("max"),
  MIN("min");

  @Getter private final String value;

  SelectOperation(String value) {
    this.value = value;
  }

  public static List<SelectOperation> aggregateOperations() {
    return Arrays.asList(COUNT, SUM, AVG, MAX, MIN);
  }
}
