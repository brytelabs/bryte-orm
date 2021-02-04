package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.core.Select;
import org.brytelabs.orm.core.domain.LinkedConjunction;
import org.brytelabs.orm.core.operations.ConjunctionOperation;
import org.brytelabs.orm.core.operations.Direction;
import org.brytelabs.orm.core.operations.Order;

public class ConjunctionBuilderImpl implements Select.ConjunctionBuilder {
    private final LinkedConjunction linkedConjunction;
    private final QueryBuilder queryBuilder;

    public ConjunctionBuilderImpl(LinkedConjunction linkedConjunction, QueryBuilder queryBuilder) {
        this.linkedConjunction = linkedConjunction;
        this.queryBuilder = queryBuilder;
    }

    @Override
    public Select.WhereBuilder and(String field) {
        return new WhereBuilderImpl(field, ConjunctionOperation.AND, linkedConjunction, queryBuilder);
    }

    @Override
    public Select.WhereBuilder or(String field) {
        return new WhereBuilderImpl(field, ConjunctionOperation.OR, linkedConjunction, queryBuilder);
    }

    @Override
    public Select.OrderByBuilder orderBy(Order... orders) {
        return new OrderByBuilderImpl(queryBuilder, orders);
    }

    @Override
    public Select.GroupByBuilder groupBy(String... fields) {
        return new GroupByBuilderImpl(fields, queryBuilder);
    }

    @Override
    public QueryBuilder build() {
        return queryBuilder;
    }
}
