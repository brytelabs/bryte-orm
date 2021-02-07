package org.brytelabs.orm.api;

import org.brytelabs.orm.core.RowMapper;
import org.brytelabs.orm.exceptions.DataAccessException;

import java.util.List;
import java.util.Optional;

public interface QueryExecutor {
    <T> Optional<T> findOne(Query query, RowMapper<T> rowMapper) throws DataAccessException;
    <T> List<T> findList(Query query, RowMapper<T> rowMapper) throws DataAccessException;
}
