package org.brytelabs.orm.api;

import org.brytelabs.orm.exceptions.SqlQueryException;
import org.brytelabs.orm.utils.ExceptionUtils;

public record Table(String name, String alias) {
  public Table {
    ExceptionUtils.passOrThrowIfNullOrEmpty(name, () -> "Table name should not be null or empty");
    ExceptionUtils.passOrThrowIfNullOrEmpty(
        alias, () -> String.format("Alias of %s table should not be null or empty", name));
    if (name.contains(" ")) {
      throw new SqlQueryException(
          "Invalid table name \"" + name + "\". Table name should not contain space");
    }
  }

  public static Table with(String name) {
    return with(name, name);
  }

  public static Table with(String name, String alias) {
    return new Table(name, alias);
  }

  @Override
  public String toString() {
    return String.format("%s %s", name, alias);
  }
}
