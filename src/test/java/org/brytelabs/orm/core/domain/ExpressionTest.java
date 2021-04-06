package org.brytelabs.orm.core.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import org.brytelabs.orm.api.Sign;
import org.brytelabs.orm.api.Table;
import org.junit.jupiter.api.Test;

class ExpressionTest {

  @Test
  public void formatExpression() {
    Table table = Table.with("user", "u");
    assertEquals(
        new Expression(Sign.EQUAL, "name", "Bright Ofosu").format(table),
        "u.name = 'Bright Ofosu'");

    assertEquals(new Expression(Sign.LESS_THAN, "age", 20).format(table), "u.age < 20");
    assertEquals(
        new Expression(Sign.IN, "gender", Arrays.asList("male", "female")).format(table),
        "u.gender in ('male','female')");
    assertEquals(
        new Expression(Sign.GREATER_THAN_OR_EQUAL, "birth_date", LocalDate.parse("2015-01-31"))
            .format(table),
        "u.birth_date >= '2015-01-31'");
  }
}
