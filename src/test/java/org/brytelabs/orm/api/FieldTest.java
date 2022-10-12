package org.brytelabs.orm.api;

import static org.junit.jupiter.api.Assertions.*;

import org.brytelabs.orm.exceptions.SqlQueryException;
import org.junit.jupiter.api.Test;

class FieldTest {

  @Test
  public void throwsExceptionIfParamsAreNotFieldCorrectly() {
    Exception exception = assertThrows(SqlQueryException.class, () -> Field.with(null));
    assertEquals(exception.getMessage(), "Field name should not be null or empty");

    exception = assertThrows(SqlQueryException.class, () -> Field.with(""));
    assertEquals(exception.getMessage(), "Field name should not be null or empty");

    exception = assertThrows(SqlQueryException.class, () -> Field.with("user", null));
    assertEquals(exception.getMessage(), "Alias of user field should not be null or empty");

    exception = assertThrows(SqlQueryException.class, () -> Field.with("user", ""));
    assertEquals(exception.getMessage(), "Alias of user field should not be null or empty");
  }

  @Test
  public void doNotThrowExceptionIfFieldAreCorrectlySet() {
    Field field = Field.with("age");
    assertEquals(field.name(), "age");
    assertEquals(field.alias(), "age");

    field = Field.with("age", "a");
    assertEquals(field.name(), "age");
    assertEquals(field.alias(), "a");
  }

  @Test
  public void tableAliasedFields() {
    Field field = Field.with("user.age");
    assertEquals(field.name(), "user.age");
    assertEquals(field.alias(), "age");

    field = Field.with("user.age", "a");
    assertEquals(field.name(), "user.age");
    assertEquals(field.alias(), "a");
  }
}
