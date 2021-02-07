package org.brytelabs.orm.core.generators;

import org.brytelabs.orm.core.builders.ConjunctionBuilderImpl;
import org.brytelabs.orm.core.builders.QueryImpl;
import org.brytelabs.orm.core.builders.WhereBuilderImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WhereGeneratorTest {

    @Test
    public void where() {
        QueryImpl query = new QueryImpl();
        ConjunctionBuilderImpl conjunction = (ConjunctionBuilderImpl) new WhereBuilderImpl("name", query)
                .eq("Bright")
                .and("age").gt(20)
                .or("age").eq(20);

//        WhereGenerator generator = new WhereGenerator(conjunction.getWhereBuilder());
//
//        String query = generator.generate();
        assertEquals(" where name = 'Bright' and age > 20 or age = 20", "");
    }
}
