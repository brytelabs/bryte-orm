package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.api.LimitBuilder;
import org.brytelabs.orm.api.OffsetBuilder;
import org.brytelabs.orm.api.Query;

public record OffsetBuilderImpl(Query query, int offset) implements OffsetBuilder {

  @Override
  public LimitBuilder limit(int limit) {
    return new LimitBuilderImpl(query, limit);
  }

  @Override
  public Query build() {
    return new QueryImpl(query.selectBuilder(), query.whereBuilder(), query.joinBuilder(),
        query.orderByBuilder(), query.groupByBuilder(), query.onBuilder(), query().limitBuilder(),
        this, query.subQuery());
  }
}
