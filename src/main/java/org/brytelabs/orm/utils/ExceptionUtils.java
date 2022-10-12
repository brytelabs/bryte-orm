package org.brytelabs.orm.utils;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import lombok.experimental.UtilityClass;
import org.brytelabs.orm.exceptions.DataAccessException;
import org.brytelabs.orm.exceptions.SqlQueryException;

@UtilityClass
public class ExceptionUtils {
  public static <T> T toDataAccessException(Callable<T> callable) {
    return toUnchecked(callable, DataAccessException::new);
  }

  public static <T> T toUnchecked(
      Callable<T> callable, Function<Throwable, RuntimeException> exception) {
    try {
      return callable.call();
    } catch (Throwable t) {
      throw exception.apply(t);
    }
  }

  public static <T> void passOrThrow(T value, Supplier<String> message, Predicate<T> predicate) {
    if (predicate.test(value)) {
      throw new SqlQueryException(message.get());
    }
  }

  public static <T> void passOrThrowIfNull(T value, Supplier<String> message) {
    passOrThrow(value, message, Objects::isNull);
  }

  public static <T> void passOrThrowIfNullOrEmpty(T value, Supplier<String> message) {
    passOrThrow(value, message, (val) -> val == null || String.valueOf(val).isBlank());
  }
}
