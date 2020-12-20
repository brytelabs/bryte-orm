package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.core.Query;
import org.brytelabs.orm.core.QueryImpl;
import org.brytelabs.orm.core.Select;
import org.brytelabs.orm.core.operations.JoinOperation;
import org.brytelabs.orm.core.operations.SelectOperation;

public class SelectBuilderImpl implements Select.SelectBuilder {
    private final SelectOperation selectOperation;
    private final String selectTable;
    private final String[] fields;

    public SelectBuilderImpl(SelectOperation selectOperation, String selectTable, String[] fields) {
        this.selectOperation = selectOperation;
        this.selectTable = selectTable;
        this.fields = fields;
    }

    @Override
    public Select.JoinBuilder leftJoin(String table) {
        return new JoinBuilderImpl(table, JoinOperation.LEFT);
    }

    @Override
    public Select.JoinBuilder rightJoin(String table) {
        return new JoinBuilderImpl(table, JoinOperation.RIGHT);
    }

    @Override
    public Select.JoinBuilder innerJoin(String table) {
        return new JoinBuilderImpl(table, JoinOperation.INNER);
    }

    @Override
    public Select.JoinBuilder outerJoin(String table) {
        return new JoinBuilderImpl(table, JoinOperation.OUTER);
    }

    @Override
    public Select.WhereBuilder where(String field) {
        return new WhereBuilderImpl(field);
    }

    @Override
    public Query build() {
        return new QueryImpl(this);
    }

    public SelectOperation getSelectOperation() {
        return selectOperation;
    }

    public String getSelectTable() {
        return selectTable;
    }

    public String[] getFields() {
        return fields;
    }
}
