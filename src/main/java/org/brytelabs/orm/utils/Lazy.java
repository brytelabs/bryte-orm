package org.brytelabs.orm.utils;

import java.util.function.Supplier;

public class Lazy<T> {
  private boolean executed;
  private T value;
  private final Supplier<T> supplier;

  private Lazy(Supplier<T> supplier) {
    this.supplier = supplier;
    this.value = null;
    this.executed = false;
  }

  public static <T> Lazy<T> of(Supplier<T> supplier) {
    return new Lazy<>(supplier);
  }

  public T get() {
    synchronized (this) {
      if (executed) {
        return value;
      }
      value = supplier.get();
      executed = true;
      return value;
    }
  }
}
