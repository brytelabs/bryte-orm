package org.brytelabs.orm.core.descriptor;

import org.brytelabs.orm.api.Field;
import org.brytelabs.orm.api.ProjectionDescriptor;

import java.util.ArrayList;
import java.util.List;

public class SelectAllDescriptor implements ProjectionDescriptor {

    @Override
    public String operation() {
        return "select ";
    }

    @Override
    public List<Field> fields() {
        return new ArrayList<>();
    }
}
