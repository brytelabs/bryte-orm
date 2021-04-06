package org.brytelabs.orm;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.brytelabs.orm.api.Query;
import org.brytelabs.orm.core.ResultToTypeMapper;
import org.brytelabs.orm.core.generators.Generator;
import org.brytelabs.orm.core.generators.QueryGenerator;
import org.brytelabs.orm.exceptions.DataAccessException;
import org.brytelabs.orm.utils.ExceptionUtils;

public class SqlQueryExecutor implements QueryExecutor {
  private static final Logger log = Logger.getLogger(SqlQueryExecutor.class.getName());

  private final Connection connection;
  private final boolean showSql;

  public SqlQueryExecutor(Connection connection) {
    this.connection = connection;
    this.showSql = false;
  }

  public SqlQueryExecutor(Connection connection, boolean showSql) {
    this.connection = connection;
    this.showSql = showSql;
  }

  public SqlQueryExecutor(DataSource dataSource) {
    this.connection = ExceptionUtils.toDataAccessException(dataSource::getConnection);
    this.showSql = false;
  }

  public SqlQueryExecutor(DataSource dataSource, boolean showSql) {
    this.connection = ExceptionUtils.toDataAccessException(dataSource::getConnection);
    this.showSql = showSql;
  }

  @Override
  public <T> Optional<T> findOne(Query query, RowMapper<T> rowMapper) {
    List<T> results = findList(query, rowMapper);
    if (results.size() > 1) {
      throw new DataAccessException("Expected only 1 result but returned " + results.size());
    }
    return results.stream().findFirst();
  }

  @Override
  public <T> Optional<T> findOne(Query query, Class<T> returnType) {
    List<T> results = findList(query, returnType);
    if (results.size() > 1) {
      throw new DataAccessException("Expected only 1 result but returned " + results.size());
    }
    return results.stream().findFirst();
  }

  @Override
  public <T> List<T> findList(Query query, RowMapper<T> rowMapper) {
    Generator generator = new QueryGenerator(query);
    String sql = generator.generate();
    if (showSql) {
      log.info(sql);
    }

    return ExceptionUtils.toDataAccessException(
        () -> {
          final List<T> list = new ArrayList<>();
          CallableStatement statement = connection.prepareCall(sql);
          ResultSet resultSet = statement.executeQuery();
          if (resultSet.isBeforeFirst()) {
            resultSet.next();
          }

          Result result = new Result(resultSet);
          while (true) {
            T rowData = rowMapper.map(result);
            list.add(rowData);
            if (resultSet.isLast()) {
              break;
            }
            resultSet.next();
          }
          return list;
        });
  }

  @Override
  public <T> List<T> findList(Query query, Class<T> returnType) {
    return findList(query, new ResultToTypeMapper<>(returnType));
  }

  @Override
  public <T> void execute(Query query, RowMapper<T> rowMapper) {}
}
