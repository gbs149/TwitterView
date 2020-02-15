package com.gbs149.twitterview.provider;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateProvider {

    public static final int YEAR = 2020;
    public static final int DAY = 31;
    public static final int HOUR_OF_DAY = 21;
    public static final int MINUTE = 35;
    public static final int SECOND = 5;

    public static Date getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR, Calendar.JANUARY, DAY, HOUR_OF_DAY, MINUTE, SECOND);
        return calendar.getTime();
    }

    public static void assertLocalDateTime(LocalDateTime localDateTime) {
        assertAll(
                () -> assertEquals(DateProvider.YEAR, localDateTime.getYear()),
                () -> assertEquals(Month.JANUARY, localDateTime.getMonth()),
                () -> assertEquals(DateProvider.DAY, localDateTime.getDayOfMonth()),
                () -> assertEquals(DateProvider.HOUR_OF_DAY, localDateTime.getHour()),
                () -> assertEquals(DateProvider.MINUTE, localDateTime.getMinute()),
                () -> assertEquals(DateProvider.SECOND, localDateTime.getSecond())
        );
    }

}
