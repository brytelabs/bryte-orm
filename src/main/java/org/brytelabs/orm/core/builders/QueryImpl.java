package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.api.GroupByBuilder;
import org.brytelabs.orm.api.JoinBuilder;
import org.brytelabs.orm.api.LimitBuilder;
import org.brytelabs.orm.api.OffsetBuilder;
import org.brytelabs.orm.api.OnBuilder;
import org.brytelabs.orm.api.OrderByBuilder;
import org.brytelabs.orm.api.Query;
import org.brytelabs.orm.api.SelectBuilder;
import org.brytelabs.orm.api.WhereExpressionBuilder;

public record QueryImpl(SelectBuilder selectBuilder, WhereExpressionBuilder whereBuilder, JoinBuilder joinBuilder,
                        OrderByBuilder orderByBuilder, GroupByBuilder groupByBuilder, OnBuilder onBuilder,
                        LimitBuilder limitBuilder, OffsetBuilder offsetBuilder, Query subQuery) implements Query {

    public static Query copyOf(Query query) {
        return new QueryImpl(query.selectBuilder(), query.whereBuilder(), query.joinBuilder(), query.orderByBuilder(),
                query.groupByBuilder(), query.onBuilder(), query.limitBuilder(), query.offsetBuilder(),
                query.subQuery());
    }

    public static Query empty() {
        return new QueryImpl(null, null, null, null, null, null, null, null, null);
    }
}
