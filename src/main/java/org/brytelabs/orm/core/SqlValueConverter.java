package org.brytelabs.orm.core;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SqlValueConverter {

    public static String quoteParam(Object val) {
        if (val == null) {
            return "null";
        }

        if (val instanceof Number || val instanceof Boolean) {
            return String.valueOf(val);
        }

        return String.format("'%s'", val);
    }

    public static String quoteList(Object... values) {
        return quoteList(Arrays.asList(values));
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
