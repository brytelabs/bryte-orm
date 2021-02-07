package org.brytelabs.orm.core;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SqlValueConverterTest {

    @Test
    public void quoteParam() {
        assertEquals("null", SqlValueConverter.quoteParam(null));
        assertEquals("true", SqlValueConverter.quoteParam(true));
        assertEquals("1", SqlValueConverter.quoteParam(1));
        assertEquals("1.52", SqlValueConverter.quoteParam(1.52));
        assertEquals("100.11222", SqlValueConverter.quoteParam(BigDecimal.valueOf(100.11222)));
        assertEquals("'2021-01-15'", SqlValueConverter.quoteParam(LocalDate.parse("2021-01-15")));
    }

    @Test
    public void quoteListParam() {
        assertEquals("(1,2,3,4)", SqlValueConverter.quoteList(Arrays.asList(1,2,3,4)));
        assertEquals("(1.4,0.06)", SqlValueConverter.quoteList(1.4, 0.06));
        assertEquals("(true,false)", SqlValueConverter.quoteList(true, false));
        assertEquals("(100.11222,99)", SqlValueConverter.quoteList(BigDecimal.valueOf(100.11222), 99));
        assertEquals("('2021-01-15','2021-01-16')", SqlValueConverter.quoteList(LocalDate.parse("2021-01-15"), LocalDate.parse("2021-01-16")));
        assertEquals("('a','b','c','d')", SqlValueConverter.quoteList("a", "b", "c", "d"));
    }
}
