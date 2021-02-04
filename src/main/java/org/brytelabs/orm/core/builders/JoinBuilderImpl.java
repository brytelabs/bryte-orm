package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.core.JoinCondition;
import org.brytelabs.orm.core.Select;
import org.brytelabs.orm.core.Table;
import org.brytelabs.orm.core.operations.JoinOperation;

public class JoinBuilderImpl implements Select.JoinBuilder {
    private final Table joinedTable;
    private final JoinOperation operation;
    private final QueryBuilder queryBuilder;

    public JoinBuilderImpl(Table joinedTable, JoinOperation operation, QueryBuilder queryBuilder) {
        this.joinedTable = joinedTable;
        this.operation = operation;
        this.queryBuilder = queryBuilder;
        this.queryBuilder.setJoinBuilder(this);
    }

    @Override
    public Select.OnBuilder on(JoinCondition condition) {
        return new OnBuilderImpl(condition, queryBuilder);
    }
}
