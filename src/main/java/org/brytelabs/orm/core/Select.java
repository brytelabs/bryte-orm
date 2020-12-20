package org.brytelabs.orm.core;

import org.brytelabs.orm.core.operations.Direction;

public interface Select {
    SelectBuilder select(String table);
    SelectBuilder select(String table, String... fields);
    SelectBuilder count(String table);
    SelectBuilder count(String table, String field);
    SelectBuilder sum(String table, String field);
    SelectBuilder max(String table, String field);
    SelectBuilder min(String table, String field);
    SelectBuilder avg(String table, String field);

    interface SelectBuilder {
        JoinBuilder leftJoin(String table);
        JoinBuilder rightJoin(String table);
        JoinBuilder innerJoin(String table);
        JoinBuilder outerJoin(String table);

        WhereBuilder where(String field);
        Query build();
    }

    interface JoinBuilder {
        OnBuilder on(Field field);
    }

    interface OnBuilder {
        WhereBuilder where(String field);
        Query build();
    }

    interface WhereBuilder {
        ConjunctionBuilder eq(Object value);
        OrderByBuilder orderBy(String field, Direction direction);
        GroupByBuilder groupBy(String... field);
    }

    interface ConjunctionBuilder {
        WhereBuilder and(String field);
        WhereBuilder or(String field);
        OrderByBuilder orderBy(String field, Direction direction);
        GroupByBuilder groupBy(String... field);
        Query build();
    }

    interface OrderByBuilder {
        GroupByBuilder groupBy(String... fields);
        Query build();
    }

    interface GroupByBuilder {
        OrderByBuilder orderBy(String field, Direction direction);
        Query build();
    }
}
