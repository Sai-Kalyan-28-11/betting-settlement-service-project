package com.betting.settlement.service;

import com.betting.settlement.model.BetSettlement;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RocketMQConsumerService {

    private final ObjectMapper objectMapper;

    // Mock consumer - in real implementation, use @RocketMQMessageListener
    /*
    @RocketMQMessageListener(
        topic = RocketMQConfig.BET_SETTLEMENTS_TOPIC,
        consumerGroup = "${rocketmq.consumer.group}"
    )
    */
    public void consumeBetSettlement(String message) {
        try {
            log.info("RocketMQ Consumer - Received bet settlement: {}", message);

            BetSettlement settlement = objectMapper.readValue(message, BetSettlement.class);

            // Process the settlement
            processBetSettlement(settlement);

        } catch (Exception e) {
            log.error("Error processing bet settlement", e);
        }
    }

    private void processBetSettlement(BetSettlement settlement) {
        log.info("Processing bet settlement: betId={}, userId={}, isWinner={}, payout={}",
                settlement.getBetId(),
                settlement.getUserId(),
                settlement.getIsWinner(),
                settlement.getPayout());

        if (settlement.getIsWinner()) {
            log.info(" WINNER: User {} wins {} on bet {}",
                    settlement.getUserId(),
                    settlement.getPayout(),
                    settlement.getBetId());
        } else {
            log.info(" LOSER: User {} loses {} on bet {}",
                    settlement.getUserId(),
                    settlement.getBetAmount(),
                    settlement.getBetId());
        }
    }
}