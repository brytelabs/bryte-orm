package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.api.ConjunctionBuilder;
import org.brytelabs.orm.api.Field;
import org.brytelabs.orm.api.Sign;
import org.brytelabs.orm.api.WhereBuilder;
import org.brytelabs.orm.core.domain.Expression;
import org.brytelabs.orm.core.domain.LinkedConjunction;
import org.brytelabs.orm.core.operations.ConjunctionOperation;

public final class WhereBuilderImpl implements WhereBuilder {
    private final LinkedConjunction linkedConjunction;
    private final Field field;
    private final ConjunctionOperation conjunctionOperation;
    private final QueryImpl query;

    public WhereBuilderImpl(String field, QueryImpl query) {
        this(Field.with(field), null, new LinkedConjunction(), query);
    }

    public WhereBuilderImpl(Field field, QueryImpl query) {
        this(field, null, new LinkedConjunction(), query);
    }

    WhereBuilderImpl(Field field, ConjunctionOperation conjunctionOperation, LinkedConjunction linkedConjunction, QueryImpl query) {
        this.field = field;
        this.conjunctionOperation = conjunctionOperation;
        this.linkedConjunction = linkedConjunction;
        this.query = query;
        this.query.setWhereBuilder(this);
    }

    @Override
    public ConjunctionBuilder eq(Object value) {
        return createConjunctionBuilder(value, Sign.EQUAL);
    }

    @Override
    public ConjunctionBuilder gt(Object value) {
        return createConjunctionBuilder(value, Sign.GREATER_THAN);
    }

    private ConjunctionBuilder createConjunctionBuilder(Object value, Sign sign) {
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

        return new ConjunctionBuilderImpl(linkedConjunction, query);
    }

    public LinkedConjunction getLinkedConjunction() {
        return linkedConjunction;
    }
}
