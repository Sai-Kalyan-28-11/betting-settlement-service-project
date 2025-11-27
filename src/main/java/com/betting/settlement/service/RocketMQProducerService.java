package com.betting.settlement.service;

import com.betting.settlement.config.RocketMQConfig;
import com.betting.settlement.model.BetSettlement;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RocketMQProducerService {

    private final ObjectMapper objectMapper;
    // Note: Using mock implementation as per assignment instructions

    public void sendBetSettlement(BetSettlement settlement) {
        try {
            String payload = objectMapper.writeValueAsString(settlement);

            // Mock RocketMQ producer - just log the payload
            log.info("RocketMQ Producer [MOCK] - Sending to topic '{}': {}",
                    RocketMQConfig.BET_SETTLEMENTS_TOPIC, payload);

            log.info("Settlement Details: betId={}, userId={}, isWinner={}, payout={}",
                    settlement.getBetId(),
                    settlement.getUserId(),
                    settlement.getIsWinner(),
                    settlement.getPayout());

            // If RocketMQ is set up, uncomment below:
            /*
            rocketMQTemplate.syncSend(
                RocketMQConfig.BET_SETTLEMENTS_TOPIC,
                MessageBuilder.withPayload(payload).build()
            );
            */

        } catch (Exception e) {
            log.error("Error sending bet settlement", e);
        }
    }
}