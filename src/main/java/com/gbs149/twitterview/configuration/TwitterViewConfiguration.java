package com.gbs149.twitterview.configuration;

import com.gbs149.twitterview.client.TwitterClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwitterViewConfiguration {
    @Bean
    TwitterClient twitterClient() {
        return new TwitterClient();
    }
}
