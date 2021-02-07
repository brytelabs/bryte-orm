package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.api.JoinBuilder;
import org.brytelabs.orm.api.LimitBuilder;
import org.brytelabs.orm.api.OffsetBuilder;
import org.brytelabs.orm.api.Query;
import org.brytelabs.orm.api.SelectBuilder;
import org.brytelabs.orm.api.Terminable;
import org.brytelabs.orm.api.WhereBuilder;
import org.brytelabs.orm.api.Field;
import org.brytelabs.orm.api.Table;
import org.brytelabs.orm.core.operations.JoinOperation;
import org.brytelabs.orm.core.operations.SelectOperation;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SelectBuilderImpl implements SelectBuilder {
    private final SelectOperation selectOperation;
    private final Table table;
    private final List<Field> fields;
    private final QueryImpl query;

    public SelectBuilderImpl(SelectOperation selectOperation, Table table) {
        this(selectOperation, table, Collections.emptyList());
    }

    public SelectBuilderImpl(SelectOperation selectOperation, Table table, String... fields) {
        this(selectOperation, table,
                Stream.of(fields)
                        .map(Field::with)
                        .collect(Collectors.toList()));
    }

    public SelectBuilderImpl(SelectOperation selectOperation, Table table, List<Field> fields) {
        this.selectOperation = selectOperation;
        this.table = table;
        this.fields = fields;
        this.query = new QueryImpl();
        this.query.setSelectBuilder(this);
    }

    @Override
    public JoinBuilder leftJoin(Table table) {
        return newJoinBuilder(table, JoinOperation.LEFT);
    }

    @Override
    public JoinBuilder rightJoin(Table table) {
        return newJoinBuilder(table, JoinOperation.RIGHT);
    }

    @Override
    public JoinBuilder innerJoin(Table table) {
        return newJoinBuilder(table, JoinOperation.INNER);
    }

    @Override
    public JoinBuilder outerJoin(Table table) {
        return newJoinBuilder(table, JoinOperation.OUTER);
    }

    @Override
    public WhereBuilder where(String field) {
        return new WhereBuilderImpl(field, query);
    }

    @Override
    public LimitBuilder limit(int limit) {
        return new LimitBuilderImpl(limit, query);
    }

    @Override
    public OffsetBuilder offset(int offset) {
        return new OffsetBuilderImpl(offset, query);
    }

    @Override
    public SelectBuilder subQuery(Terminable terminable) {
        query.setSubQuery(terminable.build());
        return this;
    }

    @Override
    public SelectBuilder subQuery(Query subQuery) {
        query.setSubQuery(subQuery);
        return this;
    }

    @Override
    public QueryImpl build() {
        return query;
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

    private JoinBuilder newJoinBuilder(Table joinedTable, JoinOperation operation) {
        return new JoinBuilderImpl(joinedTable, operation, query);
    }
}
