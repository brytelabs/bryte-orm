package org.brytelabs.orm.core;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class SqlValueConverter {

    public static String quoteParam(Object val) {
        if (val == null) {
            return "null";
        }

        if (val instanceof Number) {
            return String.valueOf(val);
        }

        String format = "'%s'";
        if (val instanceof LocalDate) {
            return String.format(format, DateTimeFormatter.ISO_LOCAL_DATE.format((LocalDate) val));
        }
        if (val instanceof LocalDateTime) {
            return String.format(format, DateTimeFormatter.ISO_LOCAL_DATE_TIME.format((LocalDateTime) val));
        }
        if (val instanceof Instant) {
            return String.format(format, DateTimeFormatter.ISO_INSTANT.format((Instant) val));
        }
        if (val instanceof ZonedDateTime) {
            Instant instant = ((ZonedDateTime) val).toInstant();
            return String.format(format, DateTimeFormatter.ISO_INSTANT.format(instant));
        }

        return String.format(format, val);
    }

    public static String quoteList(List<?> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("quoteList requires non null/empty list");
        }

        String quote = list.stream()
            .map(SqlValueConverter::quoteParam)
            .collect(Collectors.joining(","));
        return "(" + quote + ")";
    }
}
