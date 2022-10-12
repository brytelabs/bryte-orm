package org.brytelabs.orm.api;

import java.util.List;

public interface GroupByBuilder extends OrderBy {
    List<Field> fields();
}
