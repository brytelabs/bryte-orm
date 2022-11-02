package org.brytelabs.orm.api;

public interface ConjunctionBuilder<T extends ExpressionBuilder<T>> extends Terminable {
  T and(String field);

  T or(String field);
}
