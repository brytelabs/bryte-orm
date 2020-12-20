package org.brytelabs.orm.core;

import org.brytelabs.orm.exceptions.SqlQueryException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SqlTest {

    @Test
    public void selectAll() {
        Query query = new Sql().select("account").build();
        String sql = query.generate();

        assertEquals("select * from account", sql);
    }

    @Test
    public void selectWithFields() {
        Query query = new Sql().select("account", "id", "name", "age").build();
        String sql = query.generate();

        assertEquals("select id,name,age from account", sql);
    }

    @Test
    public void selectSum() {
        Query query = new Sql().sum("account", "salary").build();
        String sql = query.generate();

        assertEquals("select sum(salary) from account", sql);
    }
}
