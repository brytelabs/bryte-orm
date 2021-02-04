package org.brytelabs.orm.core.generators;

import org.brytelabs.orm.core.builders.ConjunctionBuilderImpl;
import org.brytelabs.orm.core.builders.QueryBuilder;
import org.brytelabs.orm.core.builders.WhereBuilderImpl;
import org.brytelabs.orm.core.operations.ConjunctionOperation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WhereGeneratorTest {

    @Test
    public void where() {
        QueryBuilder queryBuilder = new QueryBuilder();
        ConjunctionBuilderImpl conjunction = (ConjunctionBuilderImpl) new WhereBuilderImpl("name", queryBuilder)
                .eq("Bright")
                .and("age").gt(20)
                .or("age").eq(20);

//        WhereGenerator generator = new WhereGenerator(conjunction.getWhereBuilder());
//
//        String query = generator.generate();
        assertEquals(" where name = 'Bright' and age > 20 or age = 20", "");
    }
}
