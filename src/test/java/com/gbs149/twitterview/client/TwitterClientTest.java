package com.gbs149.twitterview.client;

import com.gbs149.twitterview.model.TweetResponse;
import com.gbs149.twitterview.provider.DateProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import twitter4j.*;
import twitter4j.api.SearchResource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TwitterClientTest {

    private static final String QUERY_1 = "query1";
    private static final String QUERY_2 = "query2";
    private static final String EXPECTED_QUERY = "%23query1+OR+%23query2";

    @InjectMocks
    private TwitterClient twitterClient;

    @Mock
    private Twitter twitter;

    @Mock
    private SearchResource searchResource;

    @Mock
    private QueryResult queryResult;

    @Mock
    private Status status;

    @Mock
    private User user;

    private void setUpMocks() throws TwitterException {
        when(twitter.search()).thenReturn(searchResource);
        when(searchResource.search(any(Query.class))).thenReturn(queryResult);
        when(queryResult.getTweets()).thenReturn(Collections.singletonList(status));
        when(status.getUser()).thenReturn(user);
        when(status.getCreatedAt()).thenReturn(DateProvider.getDate());
    }

    @Test
    @DisplayName("It should search Twitter with properly formatted query")
    void searchTest() throws TwitterException {
        setUpMocks();
        Query query = new Query(EXPECTED_QUERY);
        query.setResultType(Query.ResultType.recent);

        List<TweetResponse> tweetResponses = twitterClient.search(
                Arrays.asList(QUERY_1, QUERY_2),
                Query.ResultType.recent);

        verify(searchResource).search(query);
        assertEquals(1, tweetResponses.size());
    }

}
