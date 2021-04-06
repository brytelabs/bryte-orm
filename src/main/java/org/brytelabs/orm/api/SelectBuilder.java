package org.brytelabs.orm.api;

public interface SelectBuilder extends OffsetBuilder, LimitBuilder {
  default JoinBuilder leftJoin(String table) {
    return leftJoin(Table.with(table));
  }

  default JoinBuilder rightJoin(String table) {
    return rightJoin(Table.with(table));
  }

  default JoinBuilder innerJoin(String table) {
    return innerJoin(Table.with(table));
  }

  default JoinBuilder outerJoin(String table) {
    return outerJoin(Table.with(table));
  }

  JoinBuilder leftJoin(Table table);

  JoinBuilder rightJoin(Table table);

  JoinBuilder innerJoin(Table table);

  JoinBuilder outerJoin(Table table);

  WhereBuilder where(String field);

  SelectBuilder subQuery(Terminable terminable);

  SelectBuilder subQuery(Query query);
}
