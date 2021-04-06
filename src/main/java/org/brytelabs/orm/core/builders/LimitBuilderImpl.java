package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.api.LimitBuilder;
import org.brytelabs.orm.api.OffsetBuilder;

public class LimitBuilderImpl implements LimitBuilder {
  private final int limit;
  private final QueryImpl query;

  public LimitBuilderImpl(int limit, QueryImpl query) {
    this.limit = limit;
    this.query = query;
    this.query.setLimitBuilder(this);
  }

  @Override
  public OffsetBuilder offset(int offset) {
    return new OffsetBuilderImpl(offset, query);
  }

  @Override
  public QueryImpl build() {
    return query;
  }

  public int getLimit() {
    return limit;
  }
}
