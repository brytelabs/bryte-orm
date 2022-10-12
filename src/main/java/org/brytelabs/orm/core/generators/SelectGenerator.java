package org.brytelabs.orm.core.generators;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.brytelabs.orm.api.Field;
import org.brytelabs.orm.api.SelectBuilder;
import org.brytelabs.orm.api.Table;
import org.brytelabs.orm.core.builders.SelectBuilderImpl;
import org.brytelabs.orm.core.operations.SelectOperation;
import org.brytelabs.orm.exceptions.SqlQueryException;
import org.brytelabs.orm.utils.ExceptionUtils;
import org.brytelabs.orm.utils.SqlUtils;

public class SelectGenerator implements Generator {
  private final SelectBuilderImpl selectBuilder;

  public SelectGenerator(SelectBuilder selectBuilder) {
    this.selectBuilder = (SelectBuilderImpl) selectBuilder;
  }

  @Override
  public void validate() throws SqlQueryException {
    Objects.requireNonNull(selectBuilder, "SelectBuilder is required");
    Objects.requireNonNull(
        selectBuilder.getSelectOperation(), "SelectOperation is required for query");
    Objects.requireNonNull(selectBuilder.getTable(), "Table is required for query");
  }

  @Override
  public String generate() {
    StringBuilder sb = new StringBuilder("select ");
    switch (selectBuilder.getSelectOperation()) {
      case ALL -> sb.append("*");
      case FIELDS -> sb.append(delimitFields(selectBuilder.getFields(), selectBuilder.getTable()));
      default -> sb.append(
              quoteAggregateOperation(
                      selectBuilder.getSelectOperation(),
                      selectBuilder.getFields(),
                      selectBuilder.getTable()));
    }
    return sb.append(" from ").append(selectBuilder.getTable()).toString();
  }

  private String quoteAggregateOperation(
      SelectOperation operation, List<Field> fields, Table table) {
    ExceptionUtils.passOrThrow(
        fields, () -> "Aggregate operation " + operation + " requires 1 field", f -> f.size() > 1);

    Field field = fields.get(0);
    if (field.name().equals("*") || SqlUtils.isAliased(field.name())) {
      return operation.getValue() + "(" + field.forSelect() + ")";
    }

    return String.format("%s(%s.%s)", operation.getValue(), table.alias(), field.forSelect());
  }

  private String delimitFields(List<Field> fields, Table table) {
    if (fields == null) {
      throw new SqlQueryException("At least 1 field is required for select with fields");
    }
    return fields.stream()
        .map(
            f ->
                SqlUtils.isAliased(f.name()) || f.isAProjection()
                    ? f.forSelect()
                    : table.alias() + "." + f.forSelect())
        .collect(Collectors.joining(", "));
  }
}
