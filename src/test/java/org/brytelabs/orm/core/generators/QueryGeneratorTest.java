package org.brytelabs.orm.core.generators;

import static org.junit.jupiter.api.Assertions.*;

import org.brytelabs.orm.api.Query;
import org.brytelabs.orm.api.Select;
import org.brytelabs.orm.exceptions.SqlQueryException;
import org.junit.jupiter.api.Test;

class QueryGeneratorTest {

  @Test
  public void incorrectlyAliasedSelectFields() {
    Query query =
        Select.from("user", "id", "user.name", "p.user_id")
            .innerJoin("profile")
            .on("user_id", "id")
            .where("age")
            .lt(20)
            .orderAsc("id")
            .build();
    Generator generator = new QueryGenerator(query);
    SqlQueryException exception = assertThrows(SqlQueryException.class, generator::validate);
    assertEquals(
        exception.getMessage(),
        "Fields p.user_id are incorrectly aliased. Cannot be found on any of the tables");
  }

  @Test
  public void generateQueryWithFields() {
    Query query =
        Select.from("user", "user.id", "user.name", "profile.user_id")
            .innerJoin("profile")
            .on("user_id", "id")
            .where("age")
            .lt(20)
//            .groupBy("id", "name", "profile.user_id")
//            .orderAsc("id")
            .build();
    Generator generator = new QueryGenerator(query);
    assertEquals(
        generator.generate(),
        "select user.id, user.name, profile.user_id from user user "
            + "inner join profile profile on profile.user_id = user.id "
            + "where user.age < 20 "
            + "group by user.id, user.name, profile.user_id "
            + "order by user.id asc");
  }
}
