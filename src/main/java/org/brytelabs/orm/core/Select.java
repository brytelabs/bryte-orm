package org.brytelabs.orm.core;

import org.brytelabs.orm.core.builders.QueryBuilder;
import org.brytelabs.orm.core.operations.Order;

public interface Select {
    SelectBuilder select(Table table);
    SelectBuilder select(Table table, String... fields);
    SelectBuilder select(Table table, Field... fields);
    SelectBuilder count(Table table);
    SelectBuilder count(Table table, String field);
    SelectBuilder sum(Table table, String field);
    SelectBuilder max(Table table, String field);
    SelectBuilder min(Table table, String field);
    SelectBuilder avg(Table table, String field);

    default SelectBuilder select(String table) {
        return select(Table.with(table));
    }

    default SelectBuilder select(String table, String... fields) {
        return select(Table.with(table), fields);
    }

    default SelectBuilder select(String table, Field... fields) {
        return select(Table.with(table), fields);
    }

    default SelectBuilder count(String table) {
        return count(Table.with(table));
    }

    default SelectBuilder count(String table, String field) {
        return count(Table.with(table), field);
    }

    default SelectBuilder sum(String table, String field) {
        return sum(Table.with(table), field);
    }

    default SelectBuilder max(String table, String field) {
        return max(Table.with(table), field);
    }

    default SelectBuilder min(String table, String field) {
        return min(Table.with(table), field);
    }

    default SelectBuilder avg(String table, String field) {
        return avg(Table.with(table), field);
    }

    interface SelectBuilder extends Terminable {
        default JoinBuilder leftJoin(String table) {
            return leftJoin(Table.with(table));
        }

        default JoinBuilder rightJoin(String table) {
            return rightJoin(Table.with(table));
        }

        default JoinBuilder innerJoin(String table) {
            return innerJoin(Table.with(table));
        }

        default JoinBuilder outerJoin(String table) {
            return outerJoin(Table.with(table));
        }

        JoinBuilder leftJoin(Table table);
        JoinBuilder rightJoin(Table table);
        JoinBuilder innerJoin(Table table);
        JoinBuilder outerJoin(Table table);

        WhereBuilder where(String field);
    }

    interface JoinBuilder {
        OnBuilder on(JoinCondition field);
        default OnBuilder on(String referenceField, String referencedField) {
            return on(JoinCondition.equal(referenceField, referencedField));
        }
    }

    interface OnBuilder {
        WhereBuilder where(String field);
        QueryBuilder build();
    }

    interface WhereBuilder {
        ConjunctionBuilder eq(Object value);
        ConjunctionBuilder gt(Object value);
    }

    interface OrderBy extends Terminable {
        default OrderByBuilder orderAsc(String field) {
            return orderBy(Order.asc(field));
        }

        default OrderByBuilder orderDesc(String field) {
            return orderBy(Order.desc(field));
        }

        OrderByBuilder orderBy(Order... orders);
    }

    interface ConjunctionBuilder extends OrderBy {
        WhereBuilder and(String field);
        WhereBuilder or(String field);
        GroupByBuilder groupBy(String... field);
        QueryBuilder build();
    }

    interface OrderByBuilder extends Terminable {
    }

    interface GroupByBuilder extends OrderBy {
    }

    interface Terminable {
        QueryBuilder build();
    }
}
