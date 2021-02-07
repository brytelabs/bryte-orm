package org.brytelabs.orm.api;

import org.brytelabs.orm.exceptions.SqlQueryException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {
    @Test
    public void throwsExceptionIfParamsAreNotFieldCorrectly() {
        Exception exception = assertThrows(SqlQueryException.class, () -> Table.with(null));
        assertEquals(exception.getMessage(), "Table name should not be null or empty");

        exception = assertThrows(SqlQueryException.class, () -> Table.with(""));
        assertEquals(exception.getMessage(), "Table name should not be null or empty");

        exception = assertThrows(SqlQueryException.class, () -> Table.with("user", null));
        assertEquals(exception.getMessage(), "Alias of user table should not be null or empty");

        exception = assertThrows(SqlQueryException.class, () -> Table.with("user", ""));
        assertEquals(exception.getMessage(), "Alias of user table should not be null or empty");
    }

    @Test
    public void doNotThrowExceptionIfFieldAreCorrectlySet() {
        Table table = Table.with("user");
        assertEquals(table.getName(), "user");
        assertEquals(table.getAlias(), "user");

        table = Table.with("user", "u");
        assertEquals(table.getName(), "user");
        assertEquals(table.getAlias(), "u");
    }
}
