package org.brytelabs.orm.core;

import org.brytelabs.orm.core.domain.Result;

import java.util.function.Function;

@FunctionalInterface
public interface RowMapper<T> extends Function<Result, T> {

}
