package org.brytelabs.orm.core.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ResultTest {

    @Test
    public void sqlValueResult() {
        Map<String, Object> map = new HashMap<>();
        map.put("date", new Date(1614460980561L)); //Sat Feb 27 2021 21:23:00
        map.put("timestamp", new Timestamp(1614460980561L)); //Sat Feb 27 2021 21:23:00
        map.put("bool", true);
        map.put("name", "Some Name");
        map.put("decimal", BigDecimal.valueOf(200));
        map.put("int", 1);
        map.put("long", 1L);
        map.put("null", null);

        Result result = new Result(map);

        LocalDate localDate = LocalDate.of(2021, 2, 27);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.of(23, 23));

        assertNull(result.getOrNull("null", val -> "some value"));
        assertEquals(result.getLong("long"), 1L);
        assertEquals(result.getInt("int"), 1);
        assertEquals(result.getBigDecimal("decimal"), BigDecimal.valueOf(200.0));
        assertEquals(result.getString("name"), "Some Name");
        assertTrue(result.getBoolean("bool"));
        assertEquals(result.getLocalDate("date"), localDate);
//        assertEquals(result.getLocalDateTime("timestamp").withNano(0), localDateTime);
        assertEquals(result.getInstant("timestamp"), Instant.ofEpochMilli(1614460980561L));
    }

}
