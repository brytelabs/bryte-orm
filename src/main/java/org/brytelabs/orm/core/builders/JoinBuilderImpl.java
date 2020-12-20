package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.core.Field;
import org.brytelabs.orm.core.Select;
import org.brytelabs.orm.core.operations.JoinOperation;

public class JoinBuilderImpl implements Select.JoinBuilder {
    private final String joinedTable;
    private final JoinOperation operation;

    public JoinBuilderImpl(String joinedTable, JoinOperation operation) {
        this.joinedTable = joinedTable;
        this.operation = operation;
    }

    @Override
    public Select.OnBuilder on(Field field) {
        return new OnBuilderImpl(field);
    }
}
