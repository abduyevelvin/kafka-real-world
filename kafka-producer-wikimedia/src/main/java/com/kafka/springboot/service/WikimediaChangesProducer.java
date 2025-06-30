package com.kafka.springboot.service;

import com.launchdarkly.eventsource.EventSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

import static java.util.concurrent.TimeUnit.MINUTES;

@Slf4j
@Service
@RequiredArgsConstructor
public class WikimediaChangesProducer {

    @Value("${kafka.wikimedia.topic.name}")
    private String wikimediaTopicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage() throws InterruptedException {
        var eventHandler = new WikimediaChangesHandler(kafkaTemplate, wikimediaTopicName);
        var url = "https://stream.wikimedia.org/v2/stream/recentchange";
        var eventSource = new EventSource.Builder(eventHandler, URI.create(url)).build();

        eventSource.start();

        MINUTES.sleep(10);
    }
}
