package org.brytelabs.orm.api;

import org.brytelabs.orm.core.operations.SelectOperation;
import org.brytelabs.orm.utils.ExceptionUtils;
import org.brytelabs.orm.utils.SqlUtils;

public record Field(String name, String alias) {
  public Field {
    ExceptionUtils.passOrThrowIfNullOrEmpty(name, () -> "Field name should not be null or empty");
    ExceptionUtils.passOrThrowIfNullOrEmpty(
        alias, () -> "Alias of %s field should not be null or empty".formatted(name));
  }

  public static Field with(String name) {
    if (name != null) {
      String[] fieldParts = name.split("\\.");
      if (fieldParts.length > 1) {
        return with(name, fieldParts[1]);
      }
    }
    return with(name, name);
  }

  public static Field with(String name, String alias) {
    return new Field(name, alias);
  }

  public String forSelect() {
    if (alias.equalsIgnoreCase(name)) {
      return name;
    }
    String[] splits = name.split("\\.");
    if (splits.length > 1 && splits[1].equalsIgnoreCase(alias)) {
      return name;
    }
    return String.join(" as ", name, alias);
  }

  public String forCondition(Table table) {
    return SqlUtils.isAliased(name) ? name : String.join(".", table.alias(), name);
  }

  public boolean isAProjection() {
    String temp = name.replaceAll("\\s+", "");
    return SelectOperation.aggregateOperations().stream()
        .anyMatch(op -> temp.startsWith(op.getValue().toLowerCase() + "("));
  }
}
