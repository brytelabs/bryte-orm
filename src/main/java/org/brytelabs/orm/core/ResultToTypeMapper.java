package org.brytelabs.orm.core;

import java.lang.reflect.Field;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.brytelabs.orm.Result;
import org.brytelabs.orm.RowMapper;
import org.brytelabs.orm.utils.ExceptionUtils;
import org.brytelabs.orm.utils.ReflectionUtils;

public class ResultToTypeMapper<T> implements RowMapper<T> {
  private final Map<String, Field> columnFieldMap = new HashMap<>();
  private final Map<String, Field> fieldsMap = new HashMap<>();
  private final Class<T> returnType;

  public ResultToTypeMapper(Class<T> returnType) {
    this.returnType = returnType;
  }

  @Override
  public T map(Result rs) throws SQLException {
    if (fieldsMap.isEmpty()) {
      fieldsMap.putAll(ReflectionUtils.getDeclaredFieldsMap(returnType));
    }
    if (columnFieldMap.isEmpty()) {
      ResultSetMetaData metadata = rs.getResultSet().getMetaData();
      for (int i = 1; i <= metadata.getColumnCount(); i++) {
        String colName = metadata.getColumnName(i);
        String fieldKey =
            colName.replaceAll("_", "").replaceAll("-", "").replaceAll("\\s+", "").toLowerCase();
        columnFieldMap.put(colName, fieldsMap.get(fieldKey));
      }
    }
    final T result = ExceptionUtils.toDataAccessException(returnType::newInstance);
    columnFieldMap.forEach(
        (k, field) ->
            ExceptionUtils.toDataAccessException(
                () -> {
                  if (field != null) {
                    if (!field.isAccessible()) {
                      field.setAccessible(true);
                    }
                    Object val = rs.getValue(k, field.getType());
                    field.set(result, val);
                  }
                  return null;
                }));
    return result;
  }
}
