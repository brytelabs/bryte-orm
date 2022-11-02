package org.brytelabs.orm.api;

import java.util.List;
import lombok.experimental.UtilityClass;
import org.brytelabs.orm.core.builders.SelectBuilderImpl;
import org.brytelabs.orm.core.operations.SelectOperation;

@UtilityClass
public class Select {

  public static SelectBuilder from(String table) {
    return from(Table.with(table));
  }

  public static SelectBuilder from(Table table) {
    return SelectBuilderImpl.of(SelectOperation.ALL, table);
  }

  public static SelectBuilder from(String table, String... fields) {
    return SelectBuilderImpl.of(SelectOperation.FIELDS, Table.with(table), fields);
  }

  public static SelectBuilder from(Table table, String... fields) {
    return SelectBuilderImpl.of(SelectOperation.FIELDS, table, fields);
  }

  public static SelectBuilder from(Table table, Field... fields) {
    return SelectBuilderImpl.of(SelectOperation.FIELDS, table, List.of(fields));
  }

  public static SelectBuilder count(String table) {
    return count(Table.with(table), "*");
  }

  public static SelectBuilder count(Table table) {
    return SelectBuilderImpl.of(SelectOperation.COUNT, table);
  }

  public static SelectBuilder count(String table, String field) {
    return count(Table.with(table), field);
  }

  public static SelectBuilder count(Table table, String field) {
    return SelectBuilderImpl.of(SelectOperation.COUNT, table, field);
  }

  public static SelectBuilder sum(Table table, String field) {
    return SelectBuilderImpl.of(SelectOperation.SUM, table, field);
  }

  public static SelectBuilder max(Table table, String field) {
    return SelectBuilderImpl.of(SelectOperation.MAX, table, field);
  }

  public static SelectBuilder min(Table table, String field) {
    return SelectBuilderImpl.of(SelectOperation.MIN, table, field);
  }

  public static SelectBuilder avg(Table table, String field) {
    return SelectBuilderImpl.of(SelectOperation.AVG, table, field);
  }

  //    public static SelectBuilder from(SelectBuilder select) {
  //        return SelectBuilderImpl.of(SelectOperation.AVG, table, field);
  //    }
}
