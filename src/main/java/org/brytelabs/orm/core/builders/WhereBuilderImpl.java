package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.core.Expression;
import org.brytelabs.orm.core.Select;
import org.brytelabs.orm.core.domain.LinkedConjunction;
import org.brytelabs.orm.core.operations.ConjunctionOperation;
import org.brytelabs.orm.core.operations.Sign;

public class WhereBuilderImpl implements Select.WhereBuilder {
    private final LinkedConjunction linkedConjunction;
    private final String field;
    private final ConjunctionOperation conjunctionOperation;
    private final QueryBuilder queryBuilder;

    public WhereBuilderImpl(String field, QueryBuilder queryBuilder) {
        this(field, null, new LinkedConjunction(), queryBuilder);
    }

    WhereBuilderImpl(String field, ConjunctionOperation conjunctionOperation, LinkedConjunction linkedConjunction, QueryBuilder queryBuilder) {
        this.field = field;
        this.conjunctionOperation = conjunctionOperation;
        this.linkedConjunction = linkedConjunction;
        this.queryBuilder = queryBuilder;
        this.queryBuilder.setWhereBuilder(this);
    }

    @Override
    public Select.ConjunctionBuilder eq(Object value) {
        return createConjunctionBuilder(value, Sign.EQUAL);
    }

    @Override
    public Select.ConjunctionBuilder gt(Object value) {
        return createConjunctionBuilder(value, Sign.GREATER_THAN);
    }

    private Select.ConjunctionBuilder createConjunctionBuilder(Object value, Sign sign) {
        Expression expression = new Expression(sign, field, value);
        LinkedConjunction current = linkedConjunction.next();
        if (current.getLeft() == null) {
            current.setLeft(expression);
        }
        else if (current.getRight() == null) {
            current.setRight(expression);
            current.setOperation(conjunctionOperation);
        }
        else {
            current.setNextOperation(conjunctionOperation);
            current.setNext(new LinkedConjunction(expression));
        }

        return new ConjunctionBuilderImpl(linkedConjunction, queryBuilder);
    }

    public LinkedConjunction getLinkedConjunction() {
        return linkedConjunction;
    }
}
