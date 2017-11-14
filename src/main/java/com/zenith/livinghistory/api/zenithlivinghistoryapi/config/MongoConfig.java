package com.zenith.livinghistory.api.zenithlivinghistoryapi.config;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.utils.CascadingMongoEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean
    public CascadingMongoEventListener cascadingMongoEventListener() {
        return new CascadingMongoEventListener();
    }
}
