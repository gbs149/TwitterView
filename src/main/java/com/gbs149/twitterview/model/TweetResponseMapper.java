package com.gbs149.twitterview.model;

import twitter4j.Status;

import java.util.List;
import java.util.stream.Collectors;

import static com.gbs149.twitterview.util.LocalDateTimeConverter.toLocalDateTime;

public class TweetResponseMapper {
    public static TweetResponse statusToResponse(Status status) {
        return TweetResponse.builder()
                .userName(status.getUser().getName())
                .text(status.getText())
                .createdAt(toLocalDateTime(status.getCreatedAt()))
                .build();
    }

    public static List<TweetResponse> statusesToResponses(List<Status> statuses) {
        return statuses.stream()
                .map(TweetResponseMapper::statusToResponse)
                .collect(Collectors.toList());
    }
}
