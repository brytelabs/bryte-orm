package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.api.LimitBuilder;
import org.brytelabs.orm.api.OffsetBuilder;
import org.brytelabs.orm.api.Query;

public record LimitBuilderImpl(Query query, int limit) implements LimitBuilder {

  @Override
  public OffsetBuilder offset(int offset) {
    return new OffsetBuilderImpl(query, offset);
  }

  @Override
  public Query build() {
    return new QueryImpl(query.selectBuilder(), query.whereBuilder(), query.joinBuilder(),
        query.orderByBuilder(), query.groupByBuilder(), query.onBuilder(), this,
        query.offsetBuilder(), query.subQuery());
  }
}
