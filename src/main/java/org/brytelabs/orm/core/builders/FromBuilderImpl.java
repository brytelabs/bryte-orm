package org.brytelabs.orm.core.builders;

import lombok.RequiredArgsConstructor;
import org.brytelabs.orm.api.Field;
import org.brytelabs.orm.api.FromBuilder;
import org.brytelabs.orm.api.Query;
import org.brytelabs.orm.api.SelectBuilder;
import org.brytelabs.orm.api.Table;
import org.brytelabs.orm.core.operations.SelectOperation;

import java.util.List;

@RequiredArgsConstructor
public class FromBuilderImpl implements FromBuilder {
    private final Table table;
    private final SelectOperation operation;
    private final List<Field> fields;

    @Override
    public SelectBuilder from(Table table) {
        return null;
    }

    @Override
    public Query build() {
        return null;
    }
}
