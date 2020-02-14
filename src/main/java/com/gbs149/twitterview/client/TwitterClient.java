package com.gbs149.twitterview.client;

import com.gbs149.twitterview.model.TweetResponse;
import lombok.extern.slf4j.Slf4j;
import twitter4j.*;
import twitter4j.api.SearchResource;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

import static com.gbs149.twitterview.model.TweetResponseMapper.statusesToResponses;

@Slf4j
public class TwitterClient {
    Twitter twitter = TwitterFactory.getSingleton();

    public List<TweetResponse> search(List<String> hashtags, Query.ResultType resultType)
            throws TwitterException, UnsupportedEncodingException {
        SearchResource searchResource = twitter.search();

        Query query = new Query(formatQuery(hashtags));
        query.setResultType(resultType);

        log.info(query.toString());

        QueryResult queryResult = searchResource.search(query);
        return statusesToResponses(queryResult.getTweets());
    }

    private String formatQuery(List<String> query) throws UnsupportedEncodingException {
        String queryString = query.stream()
                .map(this::addHashtag)
                .collect(Collectors.joining(" OR "));

        return URLEncoder.encode(queryString, "UTF-8");
    }

    private String addHashtag(String query) {
        return "#" + query;
    }

}
