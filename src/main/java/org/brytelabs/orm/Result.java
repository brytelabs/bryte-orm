package org.brytelabs.orm;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.util.function.Function;
import org.brytelabs.orm.utils.SqlUtils;

public record Result(ResultSet resultSet) {

  public int getInt(String column) throws SQLException {
    return resultSet.getInt(column);
  }

  public short getShort(String column) throws SQLException {
    return resultSet.getShort(column);
  }

  public float getFloat(String column) throws SQLException {
    return resultSet.getFloat(column);
  }

  public long getLong(String column) throws SQLException {
    return resultSet.getLong(column);
  }

  public String getString(int columnIndex) throws SQLException {
    return resultSet.getString(columnIndex);
  }

  public boolean getBoolean(int columnIndex) throws SQLException {
    return resultSet.getBoolean(columnIndex);
  }

  public byte getByte(int columnIndex) throws SQLException {
    return resultSet.getByte(columnIndex);
  }

  public short getShort(int columnIndex) throws SQLException {
    return resultSet.getShort(columnIndex);
  }

  public int getInt(int columnIndex) throws SQLException {
    return resultSet.getInt(columnIndex);
  }

  public long getLong(int columnIndex) throws SQLException {
    return resultSet.getLong(columnIndex);
  }

  public float getFloat(int columnIndex) throws SQLException {
    return resultSet.getFloat(columnIndex);
  }

  public double getDouble(int columnIndex) throws SQLException {
    return resultSet.getDouble(columnIndex);
  }

  public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
    return resultSet.getBigDecimal(columnIndex);
  }

  public byte[] getBytes(int columnIndex) throws SQLException {
    return resultSet.getBytes(columnIndex);
  }

  public Date getDate(int columnIndex) throws SQLException {
    return resultSet.getDate(columnIndex);
  }

  public Time getTime(int columnIndex) throws SQLException {
    return resultSet.getTime(columnIndex);
  }

  public Timestamp getTimestamp(int columnIndex) throws SQLException {
    return resultSet.getTimestamp(columnIndex);
  }

  public String getString(String column) throws SQLException {
    return resultSet.getString(column);
  }

  public boolean getBoolean(String column) throws SQLException {
    return resultSet.getBoolean(column);
  }

  public byte getByte(String columnLabel) throws SQLException {
    return resultSet.getByte(columnLabel);
  }

  public double getDouble(String column) throws SQLException {
    return resultSet.getDouble(column);
  }

  public byte[] getBytes(String columnLabel) throws SQLException {
    return resultSet.getBytes(columnLabel);
  }

  public Date getDate(String columnLabel) throws SQLException {
    return resultSet.getDate(columnLabel);
  }

  public Time getTime(String columnLabel) throws SQLException {
    return resultSet.getTime(columnLabel);
  }

  public Timestamp getTimestamp(String columnLabel) throws SQLException {
    return resultSet.getTimestamp(columnLabel);
  }

  public Object getObject(int columnIndex) throws SQLException {
    return resultSet.getObject(columnIndex);
  }

  public Object getObject(String columnLabel) throws SQLException {
    return resultSet.getObject(columnLabel);
  }

  public BigDecimal getBigDecimal(String column) throws SQLException {
    return resultSet.getBigDecimal(column);
  }

  public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
    return resultSet.getBigDecimal(columnIndex);
  }

  public LocalDate getLocalDate(String column) throws SQLException {
    Date date = resultSet.getDate(column);
    return date != null ? date.toLocalDate() : null;
  }

  public LocalTime getLocalTime(String column) throws SQLException {
    Time date = resultSet.getTime(column);
    return date != null ? date.toLocalTime() : null;
  }

  public LocalDateTime getLocalDateTime(String column) throws SQLException {
    Timestamp date = resultSet.getTimestamp(column);
    return date != null ? date.toLocalDateTime() : null;
  }

  public ZonedDateTime getZonedDateTime(String column) throws SQLException {
    return getZonedDateTime(column, ZoneOffset.UTC);
  }

  public ZonedDateTime getZonedDateTime(String column, ZoneOffset offset) throws SQLException {
    Instant date = getInstant(column);
    return date == null ? null : date.atZone(offset);
  }

  public Instant getInstant(String column) throws SQLException {
    return getOrNull(column, value -> SqlUtils.fromSqlDate(Instant.class, value));
  }

  public <T> T getOrNull(String column, Function<Object, T> parser) throws SQLException {
    Object value = resultSet.getObject(column);
    return value == null ? null : parser.apply(value);
  }

  public <T extends Enum<T>> T getEnum(String col, Class<T> enumClass) throws SQLException {
    return Enum.valueOf(enumClass, getString(col));
  }

  @SuppressWarnings("unchecked")
  public Object getValue(String col, Class<?> type) throws SQLException {
    if (type == int.class) {
      return getInt(col);
    } else if (type == double.class) {
      return getDouble(col);
    } else if (type == short.class) {
      return getShort(col);
    } else if (type == long.class) {
      return getLong(col);
    } else if (type == float.class) {
      return getFloat(col);
    } else if (type == boolean.class) {
      return getBoolean(col);
    } else if (type == BigDecimal.class) {
      return getBigDecimal(col);
    } else if (type.isEnum()) {
      Class enumClass = type;
      return getEnum(col, enumClass);
    } else if (Temporal.class.isAssignableFrom(type)) {
      return SqlUtils.fromSqlDate(type, getObject(col));
    } else if (type == byte.class) {
      return getByte(col);
    } else if (type == byte[].class) {
      return getBytes(col);
    }

    Object val = getObject(col);
    if (val == null) {
      return null;
    }
    if (type == Double.class) {
      return Double.valueOf(val.toString());
    }
    if (type == Integer.class) {
      return Integer.valueOf(val.toString());
    }
    if (type == Long.class) {
      return Long.valueOf(val.toString());
    }
    if (type == Boolean.class) {
      return Boolean.valueOf(val.toString());
    }
    if (type == Short.class) {
      return Short.valueOf(val.toString());
    }
    if (type == Float.class) {
      return Float.valueOf(val.toString());
    }
    return val;
  }

  public ResultSet getResultSet() {
    return resultSet;
  }
}
