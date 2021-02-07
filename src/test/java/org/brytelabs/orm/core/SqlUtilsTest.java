package org.brytelabs.orm.core;

import org.brytelabs.orm.utils.SqlUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SqlUtilsTest {

    @Test
    public void quoteParam() {
        assertEquals("null", SqlUtils.quoteParam(null));
        assertEquals("true", SqlUtils.quoteParam(true));
        assertEquals("1", SqlUtils.quoteParam(1));
        assertEquals("1.52", SqlUtils.quoteParam(1.52));
        assertEquals("100.11222", SqlUtils.quoteParam(BigDecimal.valueOf(100.11222)));
        assertEquals("'2021-01-15'", SqlUtils.quoteParam(LocalDate.parse("2021-01-15")));
    }

    @Test
    public void quoteListParam() {
        assertEquals("(1,2,3,4)", SqlUtils.quoteParam(Arrays.asList(1,2,3,4)));
        assertEquals("(1.4,0.06)", SqlUtils.quoteParam(Arrays.asList(1.4, 0.06)));
        assertEquals("(true,false)", SqlUtils.quoteParam(Arrays.asList(true, false)));
        assertEquals("(100.11222,99)", SqlUtils.quoteParam(Arrays.asList(BigDecimal.valueOf(100.11222), BigDecimal.valueOf(99))));
        assertEquals("('2021-01-15','2021-01-16')", SqlUtils.quoteParam(Arrays.asList(LocalDate.parse("2021-01-15"), LocalDate.parse("2021-01-16"))));
        assertEquals("('a','b','c','d')", SqlUtils.quoteParam(Arrays.asList("a", "b", "c", "d")));
    }

    @Test
    public void isAliased() {
        assertTrue(SqlUtils.isAliased("user.name"));
        assertTrue(SqlUtils.isAliased("u.name"));
        assertFalse(SqlUtils.isAliased("name"));
        assertFalse(SqlUtils.isAliased("id"));
    }
}
