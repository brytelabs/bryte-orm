package org.brytelabs.orm.api;

import static org.junit.jupiter.api.Assertions.*;

import org.brytelabs.orm.exceptions.SqlQueryException;
import org.junit.jupiter.api.Test;

class TableTest {
  @Test
  public void throwsExceptionIfParamsAreNotFieldCorrectly() {
    Exception exception = assertThrows(SqlQueryException.class, () -> Table.with(null));
    assertEquals(exception.getMessage(), "Table name should not be null or empty");

    exception = assertThrows(SqlQueryException.class, () -> Table.with(""));
    assertEquals(exception.getMessage(), "Table name should not be null or empty");

    exception = assertThrows(SqlQueryException.class, () -> Table.with("user", null));
    assertEquals(exception.getMessage(), "Alias of user table should not be null or empty");

    exception = assertThrows(SqlQueryException.class, () -> Table.with("user", ""));
    assertEquals(exception.getMessage(), "Alias of user table should not be null or empty");

    exception = assertThrows(SqlQueryException.class, () -> Table.with("user rd"));
    assertEquals(
        exception.getMessage(),
        "Invalid table name \"user rd\". Table name should not contain space");
  }

  @Test
  public void doNotThrowExceptionIfFieldAreCorrectlySet() {
    Table table = Table.with("user");
    assertEquals(table.name(), "user");
    assertEquals(table.alias(), "user");

    table = Table.with("user", "u");
    assertEquals(table.name(), "user");
    assertEquals(table.alias(), "u");
  }
}
