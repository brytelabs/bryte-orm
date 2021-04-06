package org.brytelabs.orm.api;

import lombok.Value;
import org.brytelabs.orm.core.operations.SelectOperation;
import org.brytelabs.orm.utils.ExceptionUtils;
import org.brytelabs.orm.utils.SqlUtils;

@Value
public class Field {
  String name;
  String alias;

  public static Field with(String name) {
    if (name != null) {
      String[] splits = name.split("\\.");
      if (splits.length > 1) {
        return with(name, splits[1]);
      }
    }
    return with(name, name);
  }

  public static Field with(String name, String alias) {
    ExceptionUtils.passOrThrowIfNullOrEmpty(name, () -> "Field name should not be null or empty");
    ExceptionUtils.passOrThrowIfNullOrEmpty(
        alias, () -> String.format("Alias of %s field should not be null or empty", name));
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
    return SqlUtils.isAliased(name) ? name : String.join(".", table.getAlias(), name);
  }

  public boolean isAProjection() {
    String temp = name.replaceAll("\\s+", "");
    return SelectOperation.aggregateOperations().stream()
        .anyMatch(op -> temp.startsWith(op.getValue().toLowerCase() + "("));
  }
}
