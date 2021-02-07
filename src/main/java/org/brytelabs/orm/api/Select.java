package org.brytelabs.orm.api;

import lombok.experimental.UtilityClass;
import org.brytelabs.orm.core.builders.SelectBuilderImpl;
import org.brytelabs.orm.core.operations.SelectOperation;

import java.util.Arrays;

@UtilityClass
public class Select {
    
    public static SelectBuilder from(String table) {
        return from(Table.with(table));
    }

    public static SelectBuilder from(Table table) {
        return new SelectBuilderImpl(SelectOperation.ALL, table);
    }

    public static SelectBuilder from(Table table, String... fields) {
        return new SelectBuilderImpl(SelectOperation.FIELDS, table, fields);
    }

    public static SelectBuilder from(Table table, Field... fields) {
        return new SelectBuilderImpl(SelectOperation.FIELDS, table, Arrays.asList(fields));
    }

    public static SelectBuilder count(String table) {
        return count(Table.with(table));
    }

    public static SelectBuilder count(Table table) {
        return new SelectBuilderImpl(SelectOperation.COUNT, table);
    }

    public static SelectBuilder count(Table table, String field) {
        return new SelectBuilderImpl(SelectOperation.COUNT, table, field);
    }

    public static SelectBuilder sum(Table table, String field) {
        return new SelectBuilderImpl(SelectOperation.SUM, table, field);
    }


    public static SelectBuilder max(Table table, String field) {
        return new SelectBuilderImpl(SelectOperation.MAX, table, field);
    }


    public static SelectBuilder min(Table table, String field) {
        return new SelectBuilderImpl(SelectOperation.MIN, table, field);
    }


    public static SelectBuilder avg(Table table, String field) {
        return new SelectBuilderImpl(SelectOperation.AVG, table, field);
    }

//    public static SelectBuilder from(SelectBuilder select) {
//        return new SelectBuilderImpl(SelectOperation.AVG, table, field);
//    }
}
