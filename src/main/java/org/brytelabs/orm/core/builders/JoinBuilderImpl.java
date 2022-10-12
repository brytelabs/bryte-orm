package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.api.JoinBuilder;
import org.brytelabs.orm.api.OnBuilder;
import org.brytelabs.orm.api.Query;
import org.brytelabs.orm.api.Table;
import org.brytelabs.orm.core.domain.JoinCondition;
import org.brytelabs.orm.core.operations.JoinOperation;

public final class JoinBuilderImpl implements JoinBuilder {
  private final Table joinedTable;
  private final JoinOperation operation;
  private final Query query;

  public JoinBuilderImpl(Table joinedTable, JoinOperation operation, Query query) {
    this.joinedTable = joinedTable;
    this.operation = operation;
    this.query = new QueryImpl(query.selectBuilder(), query.whereBuilder(), this,
                               query.orderByBuilder(), query.groupByBuilder(), query.onBuilder(), query.limitBuilder(),
                               query.offsetBuilder(), query.subQuery());
  }

  @Override
  public OnBuilder on(JoinCondition condition) {
    return new OnBuilderImpl(condition, query);
  }

  public Table getJoinedTable() {
    return joinedTable;
  }

  public JoinOperation getOperation() {
    return operation;
  }
}
