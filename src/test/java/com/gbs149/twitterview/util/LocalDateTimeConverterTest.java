package com.gbs149.twitterview.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalDateTimeConverterTest {

    private static final int YEAR = 2020;
    private static final int DAY = 31;
    private static final int HOUR_OF_DAY = 21;
    private static final int MINUTE = 35;
    private static final int SECOND = 5;

    @Test
    @DisplayName("Should convert Date to LocalDateTime")
    void toLocalDateTimeTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR, Calendar.JANUARY, DAY, HOUR_OF_DAY, MINUTE, SECOND);

        LocalDateTime localDateTime = LocalDateTimeConverter.toLocalDateTime(calendar.getTime());

        assertAll(
                () -> assertEquals(YEAR, localDateTime.getYear()),
                () -> assertEquals(Month.JANUARY, localDateTime.getMonth()),
                () -> assertEquals(DAY, localDateTime.getDayOfMonth()),
                () -> assertEquals(HOUR_OF_DAY, localDateTime.getHour()),
                () -> assertEquals(MINUTE, localDateTime.getMinute()),
                () -> assertEquals(SECOND, localDateTime.getSecond())
        );
    }

}