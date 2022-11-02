package org.brytelabs.orm.core.builders;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.brytelabs.orm.api.*;
import org.brytelabs.orm.core.domain.Expression;
import org.brytelabs.orm.core.domain.LinkedConjunction;
import org.brytelabs.orm.core.operations.ConjunctionOperation;

@Slf4j
public record WhereExpressionBuilderImpl(
    Query query,
    Field field,
    LinkedConjunction linkedConjunction,
    ConjunctionOperation conjunctionOperation) implements WhereExpressionBuilder {

    public static WhereExpressionBuilder of(Query query, Field field) {
        return new WhereExpressionBuilderImpl(query, field, new LinkedConjunction(), null);
    }

    @Override
    public WhereConjunctionBuilder eq(Object value) {
        return createWhereConjunctionBuilder(value, Sign.EQUAL);
    }

    @Override
    public WhereConjunctionBuilder eq(Field field) {
        return createWhereConjunctionBuilder(field.forSelect(), Sign.EQUAL);
    }

    @Override
    public WhereConjunctionBuilder isNull() {
        return createWhereConjunctionBuilder(null, Sign.EQUAL);
    }

    @Override
    public WhereConjunctionBuilder notEq(Object value) {
        return createWhereConjunctionBuilder(value, Sign.NOT_EQUAL);
    }

    @Override
    public WhereConjunctionBuilder notNull() {
        return notEq(null);
    }

    @Override
    public WhereConjunctionBuilder gt(Object value) {
        return createWhereConjunctionBuilder(value, Sign.GREATER_THAN);
    }

    @Override
    public WhereConjunctionBuilder lt(Object value) {
        return createWhereConjunctionBuilder(value, Sign.LESS_THAN);
    }

    @Override
    public WhereConjunctionBuilder lte(Object value) {
        return createWhereConjunctionBuilder(value, Sign.LESS_THAN_OR_EQUAL);
    }

    @Override
    public WhereConjunctionBuilder gte(Object value) {
        return createWhereConjunctionBuilder(value, Sign.GREATER_THAN_OR_EQUAL);
    }

    @Override
    public WhereConjunctionBuilder between(Object value1, Object value2) {
        return createWhereConjunctionBuilder(Arrays.asList(value1, value2), Sign.BETWEEN);
    }

    @Override
    public WhereConjunctionBuilder in(Object... values) {
        return createWhereConjunctionBuilder(Arrays.asList(values), Sign.IN);
    }

    @Override
    public WhereConjunctionBuilder in(List<?> values) {
        return createWhereConjunctionBuilder(values, Sign.IN);
    }

    @Override
    public WhereConjunctionBuilder notIn(Object... values) {
        return createWhereConjunctionBuilder(Arrays.asList(values), Sign.NOT_IN);
    }

    @Override
    public WhereConjunctionBuilder notIn(List<?> values) {
        return createWhereConjunctionBuilder(values, Sign.NOT_IN);
    }

    
    private WhereConjunctionBuilder createWhereConjunctionBuilder(Object value, Sign sign) {
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
        return new WhereConjunctionBuilderImpl(qry, linkedConjunction);
    }
}
