package org.brytelabs.orm.core.generators;

import org.brytelabs.orm.api.JoinBuilder;
import org.brytelabs.orm.api.OnBuilder;
import org.brytelabs.orm.api.Table;
import org.brytelabs.orm.core.builders.JoinBuilderImpl;
import org.brytelabs.orm.core.builders.OnBuilderImpl;
import org.brytelabs.orm.exceptions.SqlQueryException;
import org.brytelabs.orm.utils.ExceptionUtils;
import org.brytelabs.orm.utils.SqlUtils;

public class JoinGenerator implements Generator {
  private final JoinBuilderImpl joinBuilder;
  private final OnBuilderImpl onBuilder;
  private final Table referencedTable;

  public JoinGenerator(JoinBuilder joinBuilder, OnBuilder onBuilder, Table referencedTable) {
    this.joinBuilder = (JoinBuilderImpl) joinBuilder;
    this.onBuilder = (OnBuilderImpl) onBuilder;
    this.referencedTable = referencedTable;
  }

  @Override
  public void validate() throws SqlQueryException {
    ExceptionUtils.passOrThrowIfNull(
        joinBuilder.joinedTable(), () -> "Table to join on cannot be null");
    ExceptionUtils.passOrThrowIfNull(
        onBuilder.condition(), () -> "Join condition cannot be null");
  }

  @Override
  public String generate() {
    StringBuilder builder =
        new StringBuilder(joinBuilder.operation().getValue())
            .append(" ")
            .append(joinBuilder.joinedTable())
            .append(" ")
            .append("on ");

    String referenceTableAliasPrefix = joinBuilder.joinedTable().alias() + ".";
    String referencedTableAliasPrefix = referencedTable.alias() + ".";

    if (!SqlUtils.isAliased(onBuilder.condition().referencedField())) {
      builder.append(referenceTableAliasPrefix);
    }
    builder.append(onBuilder.condition().referencedField()).append(" = ");

    if (!SqlUtils.isAliased(onBuilder.condition().referencedField())) {
      builder.append(referencedTableAliasPrefix);
    }
    return builder.append(onBuilder.condition().referencedField()).toString();
  }
}
