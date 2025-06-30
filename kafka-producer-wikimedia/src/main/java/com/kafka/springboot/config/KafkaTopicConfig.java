package com.kafka.springboot.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.wikimedia.topic.name}")
    private String wikimediaTopicName;

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name(wikimediaTopicName)
                           .build();
    }
}
