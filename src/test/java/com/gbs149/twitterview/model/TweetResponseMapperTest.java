package com.gbs149.twitterview.model;

import com.gbs149.twitterview.provider.DateProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import twitter4j.Status;
import twitter4j.User;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TweetResponseMapperTest {

    public static final String NAME = "Name";
    public static final String TEXT = "Text";

    @Mock
    private Status status;

    @Mock
    private User user;

    @BeforeEach
    void setUp() {
        when(status.getUser()).thenReturn(user);
        when(user.getName()).thenReturn(NAME);
        when(status.getText()).thenReturn(TEXT);
        when(status.getCreatedAt()).thenReturn(DateProvider.getDate());
    }

    @Test
    @DisplayName("It should convert Status to TwitterResponse")
    void statusToResponseTest() {
        TweetResponse tweetResponse = TweetResponseMapper.statusToResponse(status);

        assertAll(
                () -> assertEquals(NAME, tweetResponse.getUserName()),
                () -> assertEquals(TEXT, tweetResponse.getText()),
                () -> DateProvider.assertLocalDateTime(tweetResponse.getCreatedAt())
        );
    }

    @Test
    @DisplayName("It should convert List<Status> to List<TwitterResponse>")
    void statusesToResponsesTest() {
        List<Status> statuses = Collections.singletonList(status);

        List<TweetResponse> tweetResponses = TweetResponseMapper.statusesToResponses(statuses);

        assertEquals(1, tweetResponses.size());
    }
}