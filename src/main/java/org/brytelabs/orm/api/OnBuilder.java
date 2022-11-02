package org.brytelabs.orm.api;

public interface OnBuilder extends Terminable {
  WhereExpressionBuilder where(String field);
}
