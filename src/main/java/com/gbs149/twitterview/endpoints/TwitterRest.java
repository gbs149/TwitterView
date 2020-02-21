package com.gbs149.twitterview.endpoints;

import com.gbs149.twitterview.client.TwitterClient;
import com.gbs149.twitterview.model.TweetResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import twitter4j.Query;
import twitter4j.TwitterException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/twittersearch")
@CrossOrigin(origins = "https://twitterview.netlify.com")
@AllArgsConstructor
public class TwitterRest {

    private TwitterClient twitterClient;

    @GetMapping
    public ResponseEntity<List<TweetResponse>> search(
            @RequestParam(name = "q") List<String> query,
            @RequestParam(name = "resultType") Optional<Query.ResultType> resultType) {
        try {
            return new ResponseEntity<>(twitterClient.search(query, resultType.orElse(Query.ResultType.mixed)), HttpStatus.OK);
        } catch (TwitterException e) {
            throw new ResponseStatusException(
                    HttpStatus.valueOf(e.getStatusCode()), "Erro ao consultar Twitter.", e);
        }

    }
}
