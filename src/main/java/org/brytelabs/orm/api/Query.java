package org.brytelabs.orm.api;

public interface Query {
  SelectBuilder selectBuilder();

  WhereExpressionBuilder whereBuilder();

  JoinBuilder joinBuilder();

  OrderByBuilder orderByBuilder();

  GroupByBuilder groupByBuilder();

  OnBuilder onBuilder();

  LimitBuilder limitBuilder();

  OffsetBuilder offsetBuilder();

  Query subQuery();
}
