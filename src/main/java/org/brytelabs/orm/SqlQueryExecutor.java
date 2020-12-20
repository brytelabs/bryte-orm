package org.brytelabs.orm;

import org.brytelabs.orm.core.Query;
import org.brytelabs.orm.core.QueryExecutor;
import org.brytelabs.orm.core.Result;
import org.brytelabs.orm.core.RowMapper;
import org.brytelabs.orm.exceptions.DataAccessException;
import org.brytelabs.orm.utils.ExceptionUtils;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqlQueryExecutor implements QueryExecutor {
    private final Connection connection;

    public SqlQueryExecutor(Connection connection) {
        this.connection = connection;
    }

    public SqlQueryExecutor(DataSource dataSource) {
        this.connection = ExceptionUtils.toDataAccessException(dataSource::getConnection);
    }

    @Override
    public <T> Optional<T> findOne(Query query, RowMapper<T> rowMapper) throws DataAccessException {
        List<T> results = execute(query.generate(), rowMapper);
        if (results.size() > 1) {
            throw new DataAccessException("Expected only 1 result but returned " + results.size());
        }
        return results.stream().findFirst();
    }

    @Override
    public <T> List<T> execute(String query, RowMapper<T> rowMapper) throws DataAccessException{
        return ExceptionUtils.toDataAccessException(() -> {
            final List<T> list = new ArrayList<>();
            CallableStatement statement = connection.prepareCall(query);
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
        Result result = new Result();
        ResultSetMetaData metaData = resultSet.getMetaData();
        for (int i = 0; i < metaData.getColumnCount(); i++) {
            result.add(metaData.getColumnName(i), resultSet.getObject(i));
        }
        return result;
    }
}
