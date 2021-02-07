package org.brytelabs.orm.core.generators;

import org.brytelabs.orm.api.Query;
import org.brytelabs.orm.api.Select;
import org.brytelabs.orm.api.Table;
import org.brytelabs.orm.core.builders.ConjunctionBuilderImpl;
import org.brytelabs.orm.core.builders.QueryImpl;
import org.brytelabs.orm.core.builders.WhereBuilderImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WhereGeneratorTest {

    @Test
    public void where() {
        Table table = Table.with("user");
        Query query = Select.from(table)
                .where("age").gt(20)
                .or("gender").eq("male")
                .build();

        Generator generator = new WhereGenerator(query.getWhereBuilder(), table);
        assertEquals(generator.generate(), "where user.age > 20 or user.gender = 'male'");
    }
}
