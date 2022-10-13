package org.brytelabs.orm.core.generators;

import org.brytelabs.orm.api.Table;
import org.brytelabs.orm.api.WhereBuilder;
import org.brytelabs.orm.core.builders.WhereBuilderImpl;
import org.brytelabs.orm.core.domain.LinkedConjunction;
import org.brytelabs.orm.exceptions.SqlQueryException;
import org.brytelabs.orm.utils.ExceptionUtils;

public class WhereGenerator implements Generator {
  private final WhereBuilderImpl whereBuilder;
  private final Table fromTable;

  public WhereGenerator(WhereBuilder whereBuilder, Table fromTable) {
    this.whereBuilder = (WhereBuilderImpl) whereBuilder;
    this.fromTable = fromTable;
  }

  @Override
  public void validate() throws SqlQueryException {
    ExceptionUtils.passOrThrowIfNull(whereBuilder, () -> "Where part of the query not found");
    ExceptionUtils.passOrThrowIfNull(
        whereBuilder.linkedConjunction(),
        () -> "At least 1 condition is required under the where part");
  }

  @Override
  public String generate() {
    LinkedConjunction next = whereBuilder.linkedConjunction();
    StringBuilder query = new StringBuilder("where ").append(formatLinkedConjunction(next));

    while (next.hasNext()) {
      next = next.getNext();
      query.append(formatLinkedConjunction(next));
      if (next.hasNext()) {
        query.append(next.getNextOperation().getValue());
      }
    }

    return query.toString();
  }

  private String formatLinkedConjunction(LinkedConjunction conjunction) {
    StringBuilder builder = new StringBuilder(conjunction.getLeft().format(fromTable));
    if (conjunction.getRight() != null) {
      builder
          .append(conjunction.getOperation().getValue())
          .append(conjunction.getRight().format(fromTable));
    }
    return builder.toString();
  }
}
