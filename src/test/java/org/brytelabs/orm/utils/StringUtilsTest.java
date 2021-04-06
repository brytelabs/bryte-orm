package org.brytelabs.orm.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringUtilsTest {

  @Test
  public void quoteSqlValue() {
    assertEquals(StringUtils.quoteSqlValue(1), "1");
    assertEquals(StringUtils.quoteSqlValue("1"), "'1'");
  }

  @Test
  public void capitalizeFirstLetter() {
    assertEquals(StringUtils.capitalizeFirstLetter("hello"), "Hello");
    assertEquals(StringUtils.capitalizeFirstLetter("  hello  "), "Hello");
    assertEquals(StringUtils.capitalizeFirstLetter("  h"), "H");
  }
}
