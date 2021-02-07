package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.api.JoinBuilder;
import org.brytelabs.orm.api.OnBuilder;
import org.brytelabs.orm.core.domain.JoinCondition;
import org.brytelabs.orm.api.Table;
import org.brytelabs.orm.core.operations.JoinOperation;

public final class JoinBuilderImpl implements JoinBuilder {
    private final Table joinedTable;
    private final JoinOperation operation;
    private final QueryImpl query;

    public JoinBuilderImpl(Table joinedTable, JoinOperation operation, QueryImpl query) {
        this.joinedTable = joinedTable;
        this.operation = operation;
        this.query = query;
        this.query.setJoinBuilder(this);
    }

    @Override
    public OnBuilder on(JoinCondition condition) {
        return new OnBuilderImpl(condition, query);
    }

}
