package org.brytelabs.orm.core;

import org.brytelabs.orm.core.builders.SelectBuilderImpl;
import org.brytelabs.orm.core.operations.SelectOperation;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sql implements Select {
    
    @Override
    public SelectBuilder select(Table table) {
        return select(SelectOperation.ALL, table);
    }
    
    @Override
    public SelectBuilder select(Table table, String... fields) {
        return select(SelectOperation.FIELDS, table, Objects.requireNonNull(fields));
    }

    @Override
    public SelectBuilder select(Table table, Field... fields) {
        return select(SelectOperation.FIELDS, table, Arrays.asList(fields));
    }

    @Override
    public SelectBuilder count(Table table) {
        return select(SelectOperation.COUNT, table, "*");
    }

    @Override
    public SelectBuilder count(Table table, String field) {
        return select(SelectOperation.COUNT, table, Objects.requireNonNull(field));
    }

    @Override
    public SelectBuilder sum(Table table, String field) {
        return select(SelectOperation.SUM, table, Objects.requireNonNull(field));
    }

    @Override
    public SelectBuilder max(Table table, String field) {
        return select(SelectOperation.MAX, table, Objects.requireNonNull(field));
    }

    @Override
    public SelectBuilder min(Table table, String field) {
        return select(SelectOperation.MIN, table, Objects.requireNonNull(field));
    }

    @Override
    public SelectBuilder avg(Table table, String field) {
        return select(SelectOperation.AVG, table, Objects.requireNonNull(field));
    }
    
    private SelectBuilder select(SelectOperation operation, Table table, String... fields) {
        return select(operation, table,
                Stream.of(fields).map(Field::with)
                        .collect(Collectors.toList()));
    }

    private SelectBuilder select(SelectOperation operation, Table table, List<Field> fields) {
        Objects.requireNonNull(operation);
        Objects.requireNonNull(table);
        return new SelectBuilderImpl(operation, table, fields);
    }
}
