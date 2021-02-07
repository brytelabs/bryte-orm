package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.api.OnBuilder;
import org.brytelabs.orm.api.WhereBuilder;
import org.brytelabs.orm.core.domain.JoinCondition;

public final class OnBuilderImpl implements OnBuilder {
    private final JoinCondition condition;
    private final QueryImpl query;

    public OnBuilderImpl(JoinCondition condition, QueryImpl query) {
        this.condition = condition;
        this.query = query;
        this.query.setOnBuilder(this);
    }

    @Override
    public WhereBuilder where(String field) {
        return new WhereBuilderImpl(field, query);
    }

    public JoinCondition getCondition() {
        return condition;
    }

    @Override
    public QueryImpl build() {
        return query;
    }
}
