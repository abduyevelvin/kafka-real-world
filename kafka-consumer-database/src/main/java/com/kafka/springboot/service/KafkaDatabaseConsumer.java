package com.kafka.springboot.service;

import com.kafka.springboot.entity.WikimediaData;
import com.kafka.springboot.repository.WikimediaDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaDatabaseConsumer {

    private final WikimediaDataRepository wikimediaDataRepository;

    @KafkaListener(topics = "${kafka.wikimedia.topic.name}", groupId = "${kafka.wikimedia.group.id}")
    public void consume(String eventMessage) {
        log.info("Consumed event message: {}", eventMessage);

        var wikimediaData = new WikimediaData();
        wikimediaData.setEventData(eventMessage);

        wikimediaDataRepository.save(wikimediaData);
    }
}
