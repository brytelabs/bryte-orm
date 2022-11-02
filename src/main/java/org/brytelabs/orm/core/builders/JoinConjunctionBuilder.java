package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.api.*;
import org.brytelabs.orm.core.domain.LinkedConjunction;
import org.brytelabs.orm.core.operations.ConjunctionOperation;

import java.util.List;

public record JoinConjunctionBuilder(Query query, LinkedConjunction linkedConjunction) implements ConjunctionBuilder<JoinExpressionBuilder> {

  @Override
  public JoinExpressionBuilder and(String field) {
    return null;
//    return new WhereExpressionBuilderImpl(query, Field.with(field), linkedConjunction, ConjunctionOperation.AND);
  }

  @Override
  public JoinExpressionBuilder or(String field) {
    return null;
//    return new WhereExpressionBuilderImpl(query, Field.with(field), linkedConjunction, ConjunctionOperation.OR);
  }

  @Override
  public Query build() {
    return null;
  }
}
