package com.gbs149.twitterview.endpoints;

import com.gbs149.twitterview.client.TwitterClient;
import com.gbs149.twitterview.model.TweetResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import twitter4j.Query;
import twitter4j.TwitterException;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TwitterRestTest {

    public static final List<String> QUERY = Collections.singletonList("query");
    @InjectMocks
    private TwitterRest twitterRest;

    @Mock
    private TwitterClient twitterClient;

    @Test
    @DisplayName("It should search Twitter")
    void searchTest() throws TwitterException {
        when(twitterClient.search(anyList(), any(Query.ResultType.class)))
                .thenReturn(Collections.singletonList(new TweetResponse()));

        ResponseEntity<List<TweetResponse>> responseEntity = twitterRest.search(QUERY, Query.ResultType.recent);

        verify(twitterClient).search(QUERY, Query.ResultType.recent);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().size());
    }

    @Test
    @DisplayName("It should handle TwitterException")
    void searchExceptionTest() throws TwitterException {
        TwitterException twitterException = mock(TwitterException.class);
        when(twitterException.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST.value());
        when(twitterClient.search(any(), any())).thenThrow(twitterException);

        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
                () -> twitterRest.search(QUERY, Query.ResultType.recent));

        assertTrue(responseStatusException.getMessage().contains("Erro ao consultar Twitter."));
        assertEquals(HttpStatus.BAD_REQUEST, responseStatusException.getStatus());
    }

}