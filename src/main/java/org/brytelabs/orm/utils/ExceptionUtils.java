package org.brytelabs.orm.utils;

import org.brytelabs.orm.exceptions.DataAccessException;

import java.util.concurrent.Callable;
import java.util.function.Function;

public class ExceptionUtils {
    public static <T>T toDataAccessException(Callable<T> callable) {
        return toUnchecked(callable, DataAccessException::new);
    }

    public static <T> T toUnchecked(Callable<T> callable, Function<Throwable, RuntimeException> exception) {
        try {
            return callable.call();
        } catch (Throwable t) {
            throw exception.apply(t);
        }
    }
}
