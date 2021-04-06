package org.brytelabs.orm.api;

public interface Query {
  SelectBuilder getSelectBuilder();

  WhereBuilder getWhereBuilder();

  JoinBuilder getJoinBuilder();

  OrderByBuilder getOrderByBuilder();

  GroupByBuilder getGroupByBuilder();

  OnBuilder getOnBuilder();

  LimitBuilder getLimitBuilder();

  OffsetBuilder getOffsetBuilder();

  Query getSubQuery();
}
