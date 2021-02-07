package org.brytelabs.orm;

import org.brytelabs.orm.api.Query;
import org.brytelabs.orm.api.QueryExecutor;
import org.brytelabs.orm.api.Select;
import org.junit.jupiter.api.Test;

class SqlQueryExecutorTest extends BaseIntTest {

    @Test
    public void findOne() {
        QueryExecutor executor = new SqlQueryExecutor(connection);
        Query query = Select.from("some_table")
                .where("field1").eq(1)
                .and("field2").eq("value2")
                .or("field2").eq("value3")
                .orderAsc("fsss")
                .offset(1)
                .limit(10)
                .build();

        Query q2 = Select.count("some_table")
                .subQuery(Select.from("some_table")
                        .innerJoin("next_table")
                        .on("id", "next_table_id")
                        .where("age").gt(3)
                        .or("age").eq(9))
                .limit(100)
                .build();

        System.out.println(query);
    }
}
