package org.brytelabs.orm.utils;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

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

        String quote = list.stream()
                .map(SqlUtils::quoteParam)
                .collect(Collectors.joining(","));
        return "(" + quote + ")";
    }

    public static boolean isAliased(String field) {
        return field != null && field.split("\\.").length > 1;
    }
}
