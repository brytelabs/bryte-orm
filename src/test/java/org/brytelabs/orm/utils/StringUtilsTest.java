package org.brytelabs.orm.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    public void quoteSqlValue() {
        assertEquals(StringUtils.quoteSqlValue(1), "1");
        assertEquals(StringUtils.quoteSqlValue("1"), "'1'");
    }
}
