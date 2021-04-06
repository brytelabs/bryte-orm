package org.brytelabs.orm.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import org.brytelabs.orm.exceptions.DataAccessException;

@UtilityClass
public class SqlUtils {

  public static String quoteParam(Object val) {
    if (val == null) {
      return "null";
    }

    if (val instanceof Collection) {
      return quoteList((Collection<?>) val);
    }

    if (val instanceof Number || val instanceof Boolean) {
      return String.valueOf(val);
    }

    return String.format("'%s'", val);
  }

  private static String quoteList(Collection<?> list) {
    if (list == null || list.isEmpty()) {
      throw new IllegalArgumentException("quoteList requires non null/empty list");
    }

    String quote = list.stream().map(SqlUtils::quoteParam).collect(Collectors.joining(","));
    return "(" + quote + ")";
  }

  public static boolean isAliased(String field) {
    return field != null && field.split("\\.").length > 1;
  }

  @SuppressWarnings("unchecked")
  public static <T> T fromSqlDate(Class<T> returnType, Object value) {
    if (value == null) {
      return null;
    }

    if (value instanceof Date) {
      if (returnType == LocalDate.class) {
        return (T) ((Date) value).toLocalDate();
      } else if (returnType == Instant.class) {
        return (T) ((Date) value).toInstant();
      } else if (returnType == ZonedDateTime.class) {
        return (T) ((Date) value).toInstant().atZone(ZoneOffset.UTC);
      }
    } else if (value instanceof Timestamp) {
      if (returnType == LocalDateTime.class) {
        return (T) ((Timestamp) value).toLocalDateTime();
      } else if (returnType == Instant.class) {
        return (T) ((Timestamp) value).toInstant();
      } else if (returnType == ZonedDateTime.class) {
        return (T) ((Timestamp) value).toInstant().atZone(ZoneOffset.UTC);
      }
    }
    throw new DataAccessException("Cannot convert from " + value.getClass() + " to " + returnType);
  }
}
