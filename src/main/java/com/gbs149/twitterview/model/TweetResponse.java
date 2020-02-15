package com.gbs149.twitterview.model;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Builder
public class TweetResponse implements Serializable {
    private static final long serialVersionUID = 5017165254734218760L;

    private String userName;

    private String text;

    private LocalDateTime createdAt;

}
