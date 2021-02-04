package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.core.Select.GroupByBuilder;
import org.brytelabs.orm.core.Select.JoinBuilder;
import org.brytelabs.orm.core.Select.OnBuilder;
import org.brytelabs.orm.core.Select.OrderByBuilder;
import org.brytelabs.orm.core.Select.SelectBuilder;
import org.brytelabs.orm.core.Select.WhereBuilder;

public class QueryBuilder {
    private SelectBuilder selectBuilder;
    private WhereBuilder whereBuilder;
    private JoinBuilder joinBuilder;
    private OrderByBuilder orderByBuilder;
    private GroupByBuilder groupByBuilder;
    private OnBuilder onBuilder;

    public SelectBuilder getSelectBuilder() {
        return selectBuilder;
    }

    public void setSelectBuilder(SelectBuilder selectBuilder) {
        this.selectBuilder = selectBuilder;
    }

    public WhereBuilder getWhereBuilder() {
        return whereBuilder;
    }

    public void setWhereBuilder(WhereBuilder whereBuilder) {
        this.whereBuilder = whereBuilder;
    }

    public JoinBuilder getJoinBuilder() {
        return joinBuilder;
    }

    public void setJoinBuilder(JoinBuilder joinBuilder) {
        this.joinBuilder = joinBuilder;
    }

    public OrderByBuilder getOrderByBuilder() {
        return orderByBuilder;
    }

    public void setOrderByBuilder(OrderByBuilder orderByBuilder) {
        this.orderByBuilder = orderByBuilder;
    }

    public GroupByBuilder getGroupByBuilder() {
        return groupByBuilder;
    }

    public void setGroupByBuilder(GroupByBuilder groupByBuilder) {
        this.groupByBuilder = groupByBuilder;
    }

    public OnBuilder getOnBuilder() {
        return onBuilder;
    }

    public void setOnBuilder(OnBuilder onBuilder) {
        this.onBuilder = onBuilder;
    }

}
