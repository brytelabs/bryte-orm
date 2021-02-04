package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.core.JoinCondition;
import org.brytelabs.orm.core.Select;

public class OnBuilderImpl implements Select.OnBuilder {
    private final JoinCondition condition;
    private final QueryBuilder queryBuilder;

    public OnBuilderImpl(JoinCondition condition, QueryBuilder queryBuilder) {
        this.condition = condition;
        this.queryBuilder = queryBuilder;
        this.queryBuilder.setOnBuilder(this);
    }

    @Override
    public Select.WhereBuilder where(String field) {
        return new WhereBuilderImpl(field, queryBuilder);
    }

    public JoinCondition getCondition() {
        return condition;
    }

    @Override
    public QueryBuilder build() {
        return queryBuilder;
    }
}
