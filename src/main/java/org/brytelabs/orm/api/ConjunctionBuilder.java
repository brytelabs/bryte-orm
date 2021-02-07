package org.brytelabs.orm.api;

public interface ConjunctionBuilder extends OrderBy, LimitBuilder, OffsetBuilder {
    WhereBuilder and(String field);

    WhereBuilder or(String field);

    GroupByBuilder groupBy(String... field);
}
