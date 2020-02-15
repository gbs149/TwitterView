package com.gbs149.twitterview.util;

import com.gbs149.twitterview.provider.DateProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class LocalDateTimeConverterTest {

    @Test
    @DisplayName("It should convert Date to LocalDateTime")
    void toLocalDateTimeTest() {
        LocalDateTime localDateTime = LocalDateTimeConverter.toLocalDateTime(DateProvider.getDate());

        DateProvider.assertLocalDateTime(localDateTime);
    }

}