package org.brytelabs.orm.core.builders;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.brytelabs.orm.api.Field;
import org.brytelabs.orm.api.JoinBuilder;
import org.brytelabs.orm.api.LimitBuilder;
import org.brytelabs.orm.api.OffsetBuilder;
import org.brytelabs.orm.api.Query;
import org.brytelabs.orm.api.SelectBuilder;
import org.brytelabs.orm.api.Table;
import org.brytelabs.orm.api.Terminable;
import org.brytelabs.orm.api.WhereBuilder;
import org.brytelabs.orm.core.operations.JoinOperation;
import org.brytelabs.orm.core.operations.SelectOperation;

public final class SelectBuilderImpl implements SelectBuilder {
    private final SelectOperation selectOperation;
    private final Table table;
    private final List<Field> fields;
    private final Query query;

    public SelectBuilderImpl(SelectOperation selectOperation, Table table) {
        this(selectOperation, table, null, Collections.emptyList());
    }

    public SelectBuilderImpl(SelectOperation selectOperation, Table table, Query query, String... fields) {
        this(selectOperation, table, query, Stream.of(fields).map(Field::with).collect(Collectors.toList()));
    }

    public SelectBuilderImpl(SelectOperation selectOperation, Table table, Query query, List<Field> fields) {
        this.selectOperation = selectOperation;
        this.table = table;
        this.fields = fields;
        if (query == null) {
            this.query = new QueryImpl(this);
        } else {
            this.query = new QueryImpl(query.selectBuilder(), query.whereBuilder(), query.joinBuilder(),
                    query.orderByBuilder(), query.groupByBuilder(), query.onBuilder(),
                    query.limitBuilder(), query.offsetBuilder(), query.subQuery());
        }
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
        var query2 = new QueryImpl(this, query.whereBuilder(), query.joinBuilder(), query.orderByBuilder(),
                query.groupByBuilder(), query.onBuilder(), query.limitBuilder(), query.offsetBuilder(), terminable.build());
        return new SelectBuilderImpl(this.selectOperation, this.table, query2, this.fields);
    }

    @Override
    public SelectBuilder subQuery(Query subQuery) {
        Query query2 = new QueryImpl(this, query.whereBuilder(), query.joinBuilder(), query.orderByBuilder(),
                                   query.groupByBuilder(), query.onBuilder(), query.limitBuilder(), query.offsetBuilder(), subQuery);
        return new SelectBuilderImpl(this.selectOperation, this.table, query2, this.fields);
    }

    @Override
    public Query build() {
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
