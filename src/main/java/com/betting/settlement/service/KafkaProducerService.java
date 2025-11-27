package com.betting.settlement.service;

import com.betting.settlement.config.KafkaConfig;
import com.betting.settlement.model.EventOutcome;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void publishEventOutcome(EventOutcome eventOutcome) {
        try {
            String message = objectMapper.writeValueAsString(eventOutcome);

            CompletableFuture<SendResult<String, String>> future =
                    kafkaTemplate.send(KafkaConfig.EVENT_OUTCOMES_TOPIC,
                            String.valueOf(eventOutcome.getEventId()),
                            message);

            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Published event outcome to Kafka: eventId={}, topic={}, partition={}, offset={}",
                            eventOutcome.getEventId(),
                            result.getRecordMetadata().topic(),
                            result.getRecordMetadata().partition(),
                            result.getRecordMetadata().offset());
                } else {
                    log.error("Failed to publish event outcome: eventId={}",
                            eventOutcome.getEventId(), ex);
                }
            });

        } catch (JsonProcessingException e) {
            log.error("Error serializing event outcome", e);
            throw new RuntimeException("Failed to serialize event outcome", e);
        }
    }
}