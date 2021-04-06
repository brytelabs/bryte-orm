package org.brytelabs.orm;

import java.sql.SQLException;

@FunctionalInterface
public interface RowMapper<T> {
  T map(Result rs) throws SQLException;
}
