package org.brytelabs.orm;

import org.brytelabs.orm.api.Query;
import org.brytelabs.orm.api.QueryExecutor;
import org.brytelabs.orm.core.RowMapper;
import org.brytelabs.orm.core.domain.Result;
import org.brytelabs.orm.core.generators.Generator;
import org.brytelabs.orm.core.generators.QueryGenerator;
import org.brytelabs.orm.exceptions.DataAccessException;
import org.brytelabs.orm.utils.ExceptionUtils;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

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
    public <T> Optional<T> findOne(Query query, RowMapper<T> rowMapper) throws DataAccessException {
        List<T> results = findList(query, rowMapper);
        if (results.size() > 1) {
            throw new DataAccessException("Expected only 1 result but returned " + results.size());
        }
        return results.stream().findFirst();
    }

    @Override
    public <T> List<T> findList(Query query, RowMapper<T> rowMapper) throws DataAccessException {
        Generator generator = new QueryGenerator(query);
        String sql = generator.generate();
        if (showSql) {
            log.info(sql);
        }

        return ExceptionUtils.toDataAccessException(() -> {
            final List<T> list = new ArrayList<>();
            CallableStatement statement = connection.prepareCall(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
            }

            while (true) {
                Result result = resultSetToResult(resultSet);
                T rowData = rowMapper.apply(result);
                list.add(rowData);
                if (resultSet.isLast()) {
                    break;
                }
                resultSet.next();
            }
            return list;
        });
    }

    private Result resultSetToResult(ResultSet resultSet) throws SQLException {
        final Map<String, Object> map = new HashMap<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            map.put(metaData.getColumnName(i), resultSet.getObject(i));
        }
        return new Result(map);
    }
}
