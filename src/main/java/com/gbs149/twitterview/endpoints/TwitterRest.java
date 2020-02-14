package com.gbs149.twitterview.endpoints;

import com.gbs149.twitterview.client.TwitterClient;
import com.gbs149.twitterview.model.TweetResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.Query;
import twitter4j.TwitterException;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/api/twittersearch")
@AllArgsConstructor
public class TwitterRest {

    private TwitterClient twitterClient;

    @GetMapping
    public ResponseEntity<List<TweetResponse>> search(
            @RequestParam(name = "q") List<String> query,
            @RequestParam(name = "resultType") Query.ResultType resultType)
            throws TwitterException, UnsupportedEncodingException {
        return new ResponseEntity<>(twitterClient.search(query, resultType), HttpStatus.OK);
    }
}
