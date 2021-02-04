package org.brytelabs.orm.core;

import org.brytelabs.orm.core.Select.*;
import org.brytelabs.orm.core.builders.ConjunctionBuilderImpl;
import org.brytelabs.orm.core.builders.GroupByBuilderImpl;
import org.brytelabs.orm.core.builders.JoinBuilderImpl;
import org.brytelabs.orm.core.builders.OnBuilderImpl;
import org.brytelabs.orm.core.builders.OrderByBuilderImpl;
import org.brytelabs.orm.core.builders.SelectBuilderImpl;
import org.brytelabs.orm.core.builders.WhereBuilderImpl;
import org.brytelabs.orm.core.generators.SelectGenerator;
import org.brytelabs.orm.core.generators.WhereGenerator;
import org.brytelabs.orm.exceptions.SqlQueryException;

public class QueryImpl implements Query {
    private final SelectBuilderImpl selectBuilder;
    private final WhereBuilderImpl whereBuilder;
    private final JoinBuilderImpl joinBuilder;
    private final OrderByBuilderImpl orderByBuilder;
    private final GroupByBuilderImpl groupByBuilder;
    private final OnBuilderImpl onBuilder;

    public QueryImpl(SelectBuilderImpl selectBuilder) {
        this(selectBuilder, null, null, null, null, null);
    }

    public QueryImpl(SelectBuilderImpl selectBuilder, WhereBuilderImpl whereBuilder) {
        this(selectBuilder, whereBuilder, null, null, null, null);
    }

    public QueryImpl(SelectBuilderImpl selectBuilder, WhereBuilderImpl whereBuilder, GroupByBuilderImpl groupByBuilder) {
        this(selectBuilder, whereBuilder, null, null, groupByBuilder, null);
    }

    public QueryImpl(SelectBuilderImpl selectBuilder, WhereBuilderImpl whereBuilder, JoinBuilderImpl joinBuilder, OrderByBuilderImpl orderByBuilder, GroupByBuilderImpl groupByBuilder, OnBuilderImpl onBuilder) {
        this.selectBuilder = selectBuilder;
        this.whereBuilder = whereBuilder;
        this.joinBuilder = joinBuilder;
        this.orderByBuilder = orderByBuilder;
        this.groupByBuilder = groupByBuilder;
        this.onBuilder = onBuilder;
    }

    @Override
    public String generate() {
        final StringBuilder sb = new StringBuilder();
        if (selectBuilder == null) {
            throw new SqlQueryException("Query should have at least a selectBuilder");
        }

        sb.append(new SelectGenerator(selectBuilder).generate());
        if (whereBuilder != null) {
            sb.append(" ").append(new WhereGenerator(whereBuilder).generate());
        }
        return sb.toString();
    }
}
