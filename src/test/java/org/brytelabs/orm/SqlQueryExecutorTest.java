package org.brytelabs.orm;

import org.brytelabs.orm.core.operations.Direction;
import org.brytelabs.orm.core.QueryExecutor;
import org.brytelabs.orm.core.Sql;
import org.junit.jupiter.api.Test;

class SqlQueryExecutorTest extends BaseIntTest {
    private QueryExecutor executor = new SqlQueryExecutor(connection);

    @Test
    public void findOne() {
        new Sql().select("some_table")
                .where("field1").eq(1)
                .and("field2").eq("value2")
                .or("field2").eq("value3")
                .orderAsc("fsss")
                .build();
//        Sql.select("account")
//                .where("username").eq("something")
//                .and("age").eq("")
//                .
    }
}
