package org.brytelabs.orm.api;

public interface WhereConjunctionBuilder extends ConjunctionBuilder<WhereExpressionBuilder>, OrderBy, LimitBuilder, OffsetBuilder {
  GroupByBuilder groupBy(String... fields);
}
