package org.brytelabs.orm.api;

import java.util.List;

public interface ExpressionBuilder<T extends ExpressionBuilder<T>> {
  ConjunctionBuilder<T> eq(Object value);
  ConjunctionBuilder<T> eq(Field value);

  ConjunctionBuilder<T> notEq(Object value);

  ConjunctionBuilder<T> isNull();

  ConjunctionBuilder<T> notNull();

  ConjunctionBuilder<T> gt(Object value);

  ConjunctionBuilder<T> lt(Object value);

  ConjunctionBuilder<T> lte(Object value);

  ConjunctionBuilder<T> gte(Object value);

  ConjunctionBuilder<T> in(List<?> values);

  ConjunctionBuilder<T> in(Object... values);

  ConjunctionBuilder<T> notIn(Object... values);

  ConjunctionBuilder<T> notIn(List<?> values);

  ConjunctionBuilder<T> between(Object value1, Object value2);
}
