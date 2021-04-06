package org.brytelabs.orm.core.builders;

import lombok.Getter;
import lombok.Setter;
import org.brytelabs.orm.api.GroupByBuilder;
import org.brytelabs.orm.api.JoinBuilder;
import org.brytelabs.orm.api.LimitBuilder;
import org.brytelabs.orm.api.OffsetBuilder;
import org.brytelabs.orm.api.OnBuilder;
import org.brytelabs.orm.api.OrderByBuilder;
import org.brytelabs.orm.api.Query;
import org.brytelabs.orm.api.SelectBuilder;
import org.brytelabs.orm.api.WhereBuilder;

@Getter
@Setter
public final class QueryImpl implements Query {
  private SelectBuilder selectBuilder;
  private WhereBuilder whereBuilder;
  private JoinBuilder joinBuilder;
  private OrderByBuilder orderByBuilder;
  private GroupByBuilder groupByBuilder;
  private OnBuilder onBuilder;
  private LimitBuilder limitBuilder;
  private OffsetBuilder offsetBuilder;
  private Query subQuery;
}
