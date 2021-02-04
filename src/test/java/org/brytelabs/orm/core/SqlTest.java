package org.brytelabs.orm.core;

import org.brytelabs.orm.core.builders.QueryBuilder;
import org.brytelabs.orm.core.operations.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SqlTest {

//    @Test
//    public void selectAll() {
//        Query query = new Sql().select("account").build();
//        String sql = query.generate();
//
//        assertEquals("select * from account", sql);
//    }
//
//    @Test
//    public void selectWithFields() {
//        Query query = new Sql().select("account", "id", "name", "age").build();
//        String sql = query.generate();
//
//        assertEquals("select id,name,age from account", sql);
//    }
//
//    @Test
//    public void selectSum() {
//        QueryBuilder query = new Sql().sum("account", "salary").build();
//        String sql = query.generate();
//
//        assertEquals("select sum(salary) from account", sql);
//    }

    @Test
    public void selectWithWhere() {
        QueryBuilder queryBuilder = new Sql().select(Table.with("accrual", "ac"))
                .leftJoin(Table.with("daily_balance_snapshot", "dbs"))
                .on("dbs.profile_id", "ac.profile_id")
                .where("name").eq("Bright")
                .or("age").gt(20)
                .groupBy("name")
                .orderBy(Order.asc("id"), Order.desc("age"))
                .build();
        assertNotNull(queryBuilder);
    }
}
