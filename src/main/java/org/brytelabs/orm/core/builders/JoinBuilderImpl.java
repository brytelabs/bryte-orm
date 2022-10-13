package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.api.JoinBuilder;
import org.brytelabs.orm.api.OnBuilder;
import org.brytelabs.orm.api.Query;
import org.brytelabs.orm.api.Table;
import org.brytelabs.orm.core.domain.JoinCondition;
import org.brytelabs.orm.core.operations.JoinOperation;

public record JoinBuilderImpl(Query query, Table joinedTable, JoinOperation operation) implements JoinBuilder {

  @Override
  public OnBuilder on(JoinCondition condition) {
    Query qry = new QueryImpl(query.selectBuilder(), query.whereBuilder(), this,
        query.orderByBuilder(), query.groupByBuilder(), query.onBuilder(), query.limitBuilder(),
        query.offsetBuilder(), query.subQuery());
    return new OnBuilderImpl(qry, condition);
  }
}
