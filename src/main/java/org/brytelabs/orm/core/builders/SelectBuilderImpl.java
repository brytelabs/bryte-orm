package org.brytelabs.orm.core.builders;

import java.util.List;
import java.util.Objects;
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

public record SelectBuilderImpl(
    Query query,
    SelectOperation selectOperation,
    Table table,
    List<Field> fields
) implements SelectBuilder {

    public SelectBuilderImpl {
        Objects.requireNonNull(query, "Query cannot be null");
    }
    public static SelectBuilder of(SelectOperation selectOperation, Table table) {
        return new SelectBuilderImpl(QueryImpl.empty(), selectOperation, table, List.of());
    }

    public static SelectBuilder of(SelectOperation selectOperation, Table table, String... fields) {
        return new SelectBuilderImpl(QueryImpl.empty(), selectOperation, table, Stream.of(fields).map(Field::with).toList());
    }

    public static SelectBuilder of(SelectOperation selectOperation, Table table, Query query, String... fields) {
        return new SelectBuilderImpl(query, selectOperation, table, Stream.of(fields).map(Field::with).toList());
    }

    public static SelectBuilder of(SelectOperation selectOperation, Table table, List<Field> fields) {
        return new SelectBuilderImpl(QueryImpl.empty(), selectOperation, table, fields);
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
        return WhereBuilderImpl.of(query, Field.with(field));
    }

    @Override
    public LimitBuilder limit(int limit) {
        return new LimitBuilderImpl(query, limit);
    }

    @Override
    public OffsetBuilder offset(int offset) {
        return new OffsetBuilderImpl(query, offset);
    }

    @Override
    public SelectBuilder subQuery(Terminable terminable) {
        Query qry = new QueryImpl(this, query.whereBuilder(), query.joinBuilder(), query.orderByBuilder(),
                query.groupByBuilder(), query.onBuilder(), query.limitBuilder(), query.offsetBuilder(), terminable.build());
        return new SelectBuilderImpl(qry, this.selectOperation, this.table, this.fields);
    }

    @Override
    public SelectBuilder subQuery(Query subQuery) {
        Query qry = new QueryImpl(this, query.whereBuilder(), query.joinBuilder(), query.orderByBuilder(),
                                   query.groupByBuilder(), query.onBuilder(), query.limitBuilder(), query.offsetBuilder(), subQuery);
        return new SelectBuilderImpl(qry, this.selectOperation, this.table, this.fields);
    }

    @Override
    public Query build() {
        return new QueryImpl(this, query.whereBuilder(), query.joinBuilder(),
            query.orderByBuilder(), query.groupByBuilder(), query.onBuilder(),
            query.limitBuilder(), query.offsetBuilder(), query.subQuery());
    }
    
    private JoinBuilder newJoinBuilder(Table joinedTable, JoinOperation operation) {
        return new JoinBuilderImpl(query, joinedTable, operation);
    }
}
