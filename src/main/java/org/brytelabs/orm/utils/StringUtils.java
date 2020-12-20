package org.brytelabs.orm.utils;

import java.util.function.Function;

public class StringUtils {

    public static String quoteSqlValue(Object value) {
        Function<String, String> format = (txt) -> String.format("'%s'", txt);

        if (value instanceof String) {
            return format.apply(String.valueOf(value));
        }

        return String.valueOf(value);
    }
}
