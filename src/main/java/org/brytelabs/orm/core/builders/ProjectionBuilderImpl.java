package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.api.Field;
import org.brytelabs.orm.api.FromBuilder;
import org.brytelabs.orm.api.ProjectionBuilder;

public class ProjectionBuilderImpl implements ProjectionBuilder {
    @Override
    public FromBuilder field(Field field) {
        return null;
    }

    @Override
    public FromBuilder fields(Field... fields) {
        return null;
    }

    @Override
    public FromBuilder allFields() {
        return null;
    }

    @Override
    public FromBuilder distinct(Field field) {
        return null;
    }

    @Override
    public FromBuilder count() {
        return null;
    }

    @Override
    public FromBuilder count(Field field) {
        return null;
    }

    @Override
    public FromBuilder countDistinct(Field field) {
        return null;
    }

    @Override
    public FromBuilder sum(Field field) {
        return null;
    }

    @Override
    public FromBuilder max(Field field) {
        return null;
    }

    @Override
    public FromBuilder min(Field field) {
        return null;
    }

    @Override
    public FromBuilder avg(Field field) {
        return null;
    }
}
