package org.brytelabs.orm.core;

import org.brytelabs.orm.core.Select.*;
import org.brytelabs.orm.core.builders.SelectBuilderImpl;
import org.brytelabs.orm.core.generators.SelectGenerator;
import org.brytelabs.orm.exceptions.SqlQueryException;

public class QueryImpl implements Query {
    private final SelectBuilder selectBuilder;
    private final WhereBuilder whereBuilder;
    private final JoinBuilder joinBuilder;
    private final OrderByBuilder orderByBuilder;
    private final GroupByBuilder groupByBuilder;
    private final OnBuilder onBuilder;
    private final ConjunctionBuilder conjunctionBuilder;

    public QueryImpl(SelectBuilder selectBuilder) {
        this(selectBuilder, null, null, null, null, null, null);
    }

    public QueryImpl(SelectBuilder selectBuilder, WhereBuilder whereBuilder, JoinBuilder joinBuilder, OrderByBuilder orderByBuilder, GroupByBuilder groupByBuilder, OnBuilder onBuilder, ConjunctionBuilder conjunctionBuilder) {
        this.selectBuilder = selectBuilder;
        this.whereBuilder = whereBuilder;
        this.joinBuilder = joinBuilder;
        this.orderByBuilder = orderByBuilder;
        this.groupByBuilder = groupByBuilder;
        this.onBuilder = onBuilder;
        this.conjunctionBuilder = conjunctionBuilder;
    }

    @Override
    public String generate() {
        if (selectBuilder == null) {
            throw new SqlQueryException("Query should have at least a selectBuilder");
        }

        return new SelectGenerator(((SelectBuilderImpl) selectBuilder)).generate();
    }
}
