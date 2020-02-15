package com.gbs149.twitterview.client;

import com.gbs149.twitterview.model.TweetResponse;
import lombok.SneakyThrows;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.api.SearchResource;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

import static com.gbs149.twitterview.model.TweetResponseMapper.statusesToResponses;

public class TwitterClient {
    Twitter twitter;

    public TwitterClient(Twitter twitter) {
        this.twitter = twitter;
    }

    public List<TweetResponse> search(List<String> hashtags, Query.ResultType resultType)
            throws TwitterException {
        SearchResource searchResource = twitter.search();

        Query query = new Query(formatQuery(hashtags));
        query.setResultType(resultType);

        QueryResult queryResult = searchResource.search(query);
        return statusesToResponses(queryResult.getTweets());
    }

    @SneakyThrows(UnsupportedEncodingException.class)
    private String formatQuery(List<String> query) {
        String queryString = query.stream()
                .map(this::addHashtag)
                .collect(Collectors.joining(" OR "));

        return URLEncoder.encode(queryString, "UTF-8");
    }

    private String addHashtag(String query) {
        return "#" + query;
    }

}
