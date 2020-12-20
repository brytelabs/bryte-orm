package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.core.Field;
import org.brytelabs.orm.core.Query;
import org.brytelabs.orm.core.Select;

public class OnBuilderImpl implements Select.OnBuilder {
    private final Field joinField;

    public OnBuilderImpl(Field joinField) {
        this.joinField = joinField;
    }

    @Override
    public Select.WhereBuilder where(String field) {
        return new WhereBuilderImpl(field);
    }

    @Override
    public Query build() {
        return null;
    }
}
