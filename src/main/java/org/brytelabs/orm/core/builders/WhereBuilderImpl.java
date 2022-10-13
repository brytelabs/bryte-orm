package org.brytelabs.orm.core.builders;

import java.util.Arrays;
import java.util.List;

import org.brytelabs.orm.api.*;
import org.brytelabs.orm.core.domain.Expression;
import org.brytelabs.orm.core.domain.LinkedConjunction;
import org.brytelabs.orm.core.operations.ConjunctionOperation;

public record WhereBuilderImpl(
    Query query,
    Field field,
    LinkedConjunction linkedConjunction,
    ConjunctionOperation conjunctionOperation) implements WhereBuilder {

    public static WhereBuilder of(Query query, Field field) {
        return new WhereBuilderImpl(query, field, new LinkedConjunction(), null);
    }

    @Override
    public ConjunctionBuilder eq(Object value) {
        return createConjunctionBuilder(value, Sign.EQUAL);
    }

    @Override
    public ConjunctionBuilder isNull() {
        return createConjunctionBuilder(null, Sign.EQUAL);
    }

    @Override
    public ConjunctionBuilder notEq(Object value) {
        return createConjunctionBuilder(value, Sign.NOT_EQUAL);
    }

    @Override
    public ConjunctionBuilder notNull() {
        return notEq(null);
    }

    @Override
    public ConjunctionBuilder gt(Object value) {
        return createConjunctionBuilder(value, Sign.GREATER_THAN);
    }

    @Override
    public ConjunctionBuilder lt(Object value) {
        return createConjunctionBuilder(value, Sign.LESS_THAN);
    }

    @Override
    public ConjunctionBuilder lte(Object value) {
        return createConjunctionBuilder(value, Sign.LESS_THAN_OR_EQUAL);
    }

    @Override
    public ConjunctionBuilder gte(Object value) {
        return createConjunctionBuilder(value, Sign.GREATER_THAN_OR_EQUAL);
    }

    @Override
    public ConjunctionBuilder between(Object value1, Object value2) {
        return createConjunctionBuilder(Arrays.asList(value1, value2), Sign.BETWEEN);
    }

    @Override
    public ConjunctionBuilder in(Object... values) {
        return createConjunctionBuilder(Arrays.asList(values), Sign.IN);
    }

    @Override
    public ConjunctionBuilder in(List<?> values) {
        return createConjunctionBuilder(values, Sign.IN);
    }

    @Override
    public ConjunctionBuilder notIn(Object... values) {
        return createConjunctionBuilder(Arrays.asList(values), Sign.NOT_IN);
    }

    @Override
    public ConjunctionBuilder notIn(List<?> values) {
        return createConjunctionBuilder(values, Sign.NOT_IN);
    }

    private ConjunctionBuilder createConjunctionBuilder(Object value, Sign sign) {
        Expression expression = new Expression(sign, field, value);
        LinkedConjunction current = linkedConjunction.getNext();
        if (current.getLeft() == null) {
            current.setLeft(expression);
        } else if (current.getRight() == null) {
            current.setRight(expression);
            current.setOperation(conjunctionOperation);
        } else {
            current.setNextOperation(conjunctionOperation);
            current.setNext(new LinkedConjunction(expression));
        }

        Query qry = new QueryImpl(query.selectBuilder(), this, query.joinBuilder(),
            query.orderByBuilder(), query.groupByBuilder(), query.onBuilder(), query.limitBuilder(),
            query.offsetBuilder(), query.subQuery());
        return new ConjunctionBuilderImpl(qry, linkedConjunction);
    }
}
