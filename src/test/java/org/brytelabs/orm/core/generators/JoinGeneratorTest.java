package org.brytelabs.orm.core.generators;

import static org.junit.jupiter.api.Assertions.*;

import org.brytelabs.orm.api.Query;
import org.brytelabs.orm.api.Select;
import org.brytelabs.orm.api.Table;
import org.junit.jupiter.api.Test;

class JoinGeneratorTest {

  @Test
  public void joinWithUnquotedCondition() {
    Table fromTable = Table.with("user");
    Table joinTable = Table.with("profile");
    Query query = Select.from(fromTable).leftJoin(joinTable).on("user_id", "id").build();

    Generator generator =
        new JoinGenerator(query.getJoinBuilder(), query.getOnBuilder(), fromTable);
    assertEquals(generator.generate(), "left join profile profile on profile.user_id = user.id");
  }

  @Test
  public void joinWithQuotedCondition() {
    Table fromTable = Table.with("user");
    Table joinTable = Table.with("profile");
    Query query =
        Select.from(fromTable).leftJoin(joinTable).on("profile.user_id", "user.id").build();

    Generator generator =
        new JoinGenerator(query.getJoinBuilder(), query.getOnBuilder(), fromTable);
    assertEquals(generator.generate(), "left join profile profile on profile.user_id = user.id");
  }

  @Test
  public void joinWithDefinedTableAliases() {
    Table fromTable = Table.with("user", "u");
    Table joinTable = Table.with("profile", "p");
    Query query = Select.from(fromTable).leftJoin(joinTable).on("user_id", "id").build();

    Generator generator =
        new JoinGenerator(query.getJoinBuilder(), query.getOnBuilder(), fromTable);
    assertEquals(generator.generate(), "left join profile p on p.user_id = u.id");
  }
}
