package org.brytelabs.orm.core.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.function.Function;

public class Result {
    private final Map<String, Object> map;

    public Result(Map<String, Object> map) {
        this.map = map;
    }

    public Integer getInt(String column) {
        return parseFromStringValue(column, Integer::parseInt);
    }

    public Long getLong(String column) {
        return parseFromStringValue(column, Long::parseLong);
    }

    public String getString(String column) {
        return String.valueOf(map.get(column));
    }

    public Boolean getBoolean(String column) {
        return parseFromStringValue(column, Boolean::parseBoolean);
    }

    public Double getDouble(String column) {
        return parseFromStringValue(column, Double::parseDouble);
    }

    public LocalDate getLocalDate(String column) {
        return getOrNull(column, value -> {
            if (value instanceof String) {
                return LocalDate.parse(value.toString());
            }
            else if (value instanceof Date) {
                return ((Date)value).toLocalDate();
            }
            return ((Timestamp) value).toLocalDateTime().toLocalDate();
        });
    }

    public LocalDateTime getLocalDateTime(String column) {
        return getOrNull(column, value -> {
            if (value instanceof String) {
                return LocalDateTime.parse(value.toString());
            }
            return ((Timestamp) value).toLocalDateTime();
        });
    }

    public ZonedDateTime getZonedDateTime(String column) {
        return getZonedDateTime(column, ZoneOffset.UTC);
    }

    public ZonedDateTime getZonedDateTime(String column, ZoneOffset offset) {
        Instant date = getInstant(column);
        return date == null ? null : date.atZone(offset);
    }

    public Instant getInstant(String column) {
        return getOrNull(column, value -> {
            if (value instanceof String) {
                return Instant.parse(value.toString());
            }
            else if (value instanceof Date) {
                return ((Date)value).toInstant();
            }
            return ((Timestamp) value).toInstant();
        });
    }

    public BigDecimal getBigDecimal(String column) {
        Double value = getDouble(column);
        return value == null ? null : BigDecimal.valueOf(value);
    }

    private  <T> T parseFromStringValue(String column, Function<String, T> parser) {
        Object value = map.get(column);
        return value == null ? null : parser.apply(String.valueOf(value));
    }

    public <T> T getOrNull(String column, Function<Object, T> parser) {
        Object value = map.get(column);
        return value == null ? null : parser.apply(value);
    }
}
