package org.brytelabs.orm.core;

import org.brytelabs.orm.core.builders.SelectBuilderImpl;
import org.brytelabs.orm.core.operations.SelectOperation;

import java.util.Objects;

public class Sql implements Select {
    
    @Override
    public SelectBuilder select(String table) {
        return select(SelectOperation.ALL, table);
    }
    
    @Override
    public SelectBuilder select(String table, String... fields) {
        return select(SelectOperation.FIELDS, table, Objects.requireNonNull(fields));
    }

    @Override
    public SelectBuilder count(String table) {
        return select(SelectOperation.COUNT, table, "*");
    }

    @Override
    public SelectBuilder count(String table, String field) {
        return select(SelectOperation.COUNT, table, Objects.requireNonNull(field));
    }

    @Override
    public SelectBuilder sum(String table, String field) {
        return select(SelectOperation.SUM, table, Objects.requireNonNull(field));
    }

    @Override
    public SelectBuilder max(String table, String field) {
        return select(SelectOperation.MAX, table, Objects.requireNonNull(field));
    }

    @Override
    public SelectBuilder min(String table, String field) {
        return select(SelectOperation.MIN, table, Objects.requireNonNull(field));
    }

    @Override
    public SelectBuilder avg(String table, String field) {
        return select(SelectOperation.AVG, table, Objects.requireNonNull(field));
    }
    
    private SelectBuilder select(SelectOperation operation, String table, String... fields) {
        Objects.requireNonNull(operation);
        Objects.requireNonNull(table);
        return new SelectBuilderImpl(operation, table, fields);
    }
}
