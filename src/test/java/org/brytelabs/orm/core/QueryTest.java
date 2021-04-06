package org.brytelabs.orm.core;

import static org.junit.jupiter.api.Assertions.*;

import org.brytelabs.orm.api.Order;
import org.brytelabs.orm.api.Query;
import org.brytelabs.orm.api.Select;
import org.junit.jupiter.api.Test;

class QueryTest {

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
    Query query =
        Select.from("accrual")
            .leftJoin("daily_balance_snapshot")
            .on("profile_id", "profile_id")
            .where("name")
            .eq("Bright")
            .or("age")
            .gt(20)
            .groupBy("name")
            .orderBy(Order.asc("id"), Order.desc("age"))
            .build();
    assertNotNull(query);
  }
}
