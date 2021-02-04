package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.core.Field;
import org.brytelabs.orm.core.Table;
import org.brytelabs.orm.core.Select;
import org.brytelabs.orm.core.operations.JoinOperation;
import org.brytelabs.orm.core.operations.SelectOperation;

import java.util.List;

public class SelectBuilderImpl implements Select.SelectBuilder {
    private final SelectOperation selectOperation;
    private final Table table;
    private final List<Field> fields;
    private final QueryBuilder queryBuilder;

    public SelectBuilderImpl(SelectOperation selectOperation, Table table, List<Field> fields) {
        this.selectOperation = selectOperation;
        this.table = table;
        this.fields = fields;
        this.queryBuilder = new QueryBuilder();
        this.queryBuilder.setSelectBuilder(this);
    }

    @Override
    public Select.JoinBuilder leftJoin(Table table) {
        return newJoinBuilder(table, JoinOperation.LEFT);
    }

    @Override
    public Select.JoinBuilder rightJoin(Table table) {
        return newJoinBuilder(table, JoinOperation.RIGHT);
    }

    @Override
    public Select.JoinBuilder innerJoin(Table table) {
        return newJoinBuilder(table, JoinOperation.INNER);
    }

    @Override
    public Select.JoinBuilder outerJoin(Table table) {
        return newJoinBuilder(table, JoinOperation.OUTER);
    }

    @Override
    public Select.WhereBuilder where(String field) {
        return new WhereBuilderImpl(field, queryBuilder);
    }

    @Override
    public QueryBuilder build() {
        return queryBuilder;
    }

    public SelectOperation getSelectOperation() {
        return selectOperation;
    }

    public Table getTable() {
        return table;
    }

    public List<Field> getFields() {
        return fields;
    }

    private Select.JoinBuilder newJoinBuilder(Table joinedTable, JoinOperation operation) {
        return new JoinBuilderImpl(joinedTable, operation, queryBuilder);
    }
}
