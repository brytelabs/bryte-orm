package org.brytelabs.orm.core.generators;

import static org.junit.jupiter.api.Assertions.*;

import org.brytelabs.orm.api.Select;
import org.brytelabs.orm.api.SelectBuilder;
import org.junit.jupiter.api.Test;

class SelectGeneratorTest {

  @Test
  public void selectAll() {
    SelectBuilder builder = Select.from("user");
    Generator generator = new SelectGenerator(builder);

    assertEquals(generator.generate(), "select * from user user");
  }

  @Test
  public void selectSomeFields() {
    SelectBuilder builder = Select.from("user", "id", "name", "age");
    Generator generator = new SelectGenerator(builder);

    assertEquals(generator.generate(), "select user.id, user.name, user.age from user user");
  }

  @Test
  public void selectAliasedFields() {
    SelectBuilder builder = Select.from("user", "user.id", "user.name", "age");
    Generator generator = new SelectGenerator(builder);

    assertEquals(generator.generate(), "select user.id, user.name, user.age from user user");
  }

  @Test
  public void selectCount() {
    SelectBuilder builder = Select.count("user");
    Generator generator = new SelectGenerator(builder);

    assertEquals(generator.generate(), "select count(*) from user user");
  }

  @Test
  public void selectCountWithField() {
    SelectBuilder builder = Select.count("user", "id");
    Generator generator = new SelectGenerator(builder);

    assertEquals(generator.generate(), "select count(user.id) from user user");
  }
}
