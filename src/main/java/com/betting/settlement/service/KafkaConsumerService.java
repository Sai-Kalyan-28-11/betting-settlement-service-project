package com.betting.settlement.service;

import com.betting.settlement.config.KafkaConfig;
import com.betting.settlement.model.EventOutcome;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final ObjectMapper objectMapper;
    private final BetMatchingService betMatchingService;

    @KafkaListener(topics = KafkaConfig.EVENT_OUTCOMES_TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void consumeEventOutcome(String message) {
        try {
            log.info("Received event outcome from Kafka: {}", message);

            EventOutcome eventOutcome = objectMapper.readValue(message, EventOutcome.class);

            log.info("Processing event outcome: eventId={}, eventName={}, winnerId={}",
                    eventOutcome.getEventId(),
                    eventOutcome.getEventName(),
                    eventOutcome.getEventWinnerId());

            // Match bets and send to RocketMQ
            betMatchingService.matchAndSettleBets(eventOutcome);

        } catch (Exception e) {
            log.error("Error processing event outcome message", e);
        }
    }
}