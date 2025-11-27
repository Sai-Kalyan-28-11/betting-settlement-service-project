package com.betting.settlement.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    public static final String EVENT_OUTCOMES_TOPIC = "event-outcomes";

    @Bean
    public NewTopic eventOutcomesTopic() {
        return TopicBuilder
                .name(EVENT_OUTCOMES_TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }
}