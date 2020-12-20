package org.brytelabs.orm.core;

import java.util.function.Function;

@FunctionalInterface
public interface RowMapper<T> extends Function<Result, T> {

}
