package org.brytelabs.orm;

import java.util.List;
import java.util.Optional;
import org.brytelabs.orm.api.Query;

public interface QueryExecutor {
  <T> Optional<T> findOne(Query query, RowMapper<T> rowMapper);

  <T> Optional<T> findOne(Query query, Class<T> returnType);

  <T> List<T> findList(Query query, RowMapper<T> rowMapper);

  <T> List<T> findList(Query query, Class<T> returnType);

  <T> void execute(Query query, RowMapper<T> rowMapper);
}
