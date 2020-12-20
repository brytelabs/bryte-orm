package org.brytelabs.orm.core.generators;

import org.brytelabs.orm.core.builders.SelectBuilderImpl;
import org.brytelabs.orm.core.operations.SelectOperation;
import org.brytelabs.orm.exceptions.SqlQueryException;

import java.util.Objects;

public class SelectGenerator implements Generator {
    private final SelectBuilderImpl selectBuilder;

    public SelectGenerator(SelectBuilderImpl selectBuilder) {
        this.selectBuilder = selectBuilder;
        Objects.requireNonNull(selectBuilder, "SelectBuilder is required");
        Objects.requireNonNull(selectBuilder.getSelectOperation(), "SelectOperation is required for query");
        Objects.requireNonNull(selectBuilder.getSelectTable(), "Table is required for query");
    }

    @Override
    public String generate() {
        StringBuilder sb = new StringBuilder("select ");
        switch (selectBuilder.getSelectOperation()) {
            case ALL:
                sb.append("*"); break;
            case FIELDS:
                sb.append(delimitFields(selectBuilder.getFields())); break;
            case COUNT:
            case AVG:
            case MAX:
            case MIN:
            case SUM:
                sb.append(quoteAggregateOperation(selectBuilder.getSelectOperation(), selectBuilder.getFields()));
                break;
            default:
                throw new SqlQueryException(selectBuilder.getSelectOperation() + " is not supported");
        }
        return sb.append(" from ").append(selectBuilder.getSelectTable()).toString();
    }

    private String quoteAggregateOperation(SelectOperation operation, String[] fields) {
        if (fields == null || fields.length > 1) {
            throw new SqlQueryException("Aggregate operation " + operation + " requires 1 field");
        }
        return operation.getValue() + "(" + fields[0] + ")";
    }

    private String delimitFields(String[] fields) {
        if (fields == null) {
            throw new SqlQueryException("At least 1 field is required for select with fields");
        }
        return String.join(",", fields);
    }
}
