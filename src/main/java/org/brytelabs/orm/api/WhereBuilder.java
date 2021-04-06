package org.brytelabs.orm.api;

import java.util.List;

public interface WhereBuilder {
  ConjunctionBuilder eq(Object value);

  ConjunctionBuilder notEq(Object value);

  ConjunctionBuilder isNull();

  ConjunctionBuilder notNull();

  ConjunctionBuilder gt(Object value);

  ConjunctionBuilder lt(Object value);

  ConjunctionBuilder lte(Object value);

  ConjunctionBuilder gte(Object value);

  ConjunctionBuilder in(List<?> values);

  ConjunctionBuilder in(Object... values);

  ConjunctionBuilder notIn(Object... values);

  ConjunctionBuilder notIn(List<?> values);

  ConjunctionBuilder between(Object value1, Object value2);
}
