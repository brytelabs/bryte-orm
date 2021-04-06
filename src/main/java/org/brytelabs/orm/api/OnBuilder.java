package org.brytelabs.orm.api;

public interface OnBuilder extends Terminable {
  WhereBuilder where(String field);
}
