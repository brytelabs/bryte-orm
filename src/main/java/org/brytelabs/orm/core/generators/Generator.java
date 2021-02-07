package org.brytelabs.orm.core.generators;

import org.brytelabs.orm.exceptions.SqlQueryException;

public interface Generator {
    void validate() throws SqlQueryException;
    String generate();
}
