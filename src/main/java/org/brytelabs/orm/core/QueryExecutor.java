package org.brytelabs.orm.core;

import org.brytelabs.orm.exceptions.DataAccessException;

import java.util.List;
import java.util.Optional;

public interface QueryExecutor {

    <T> Optional<T> findOne(Query query, RowMapper<T> rowMapper) throws DataAccessException;
    <T> List<T> execute(String query, RowMapper<T> rowMapper) throws DataAccessException;
}
