package com.betting.settlement.controller;

import com.betting.settlement.dto.EventOutcomeRequest;
import com.betting.settlement.model.EventOutcome;
import com.betting.settlement.service.KafkaProducerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@Slf4j
public class EventOutcomeController {

    private final KafkaProducerService kafkaProducerService;

    @PostMapping("/outcomes")
    public ResponseEntity<Map<String, Object>> publishEventOutcome(
            @Valid @RequestBody EventOutcomeRequest request) {

        log.info("Received event outcome request: eventId={}, eventName={}, winnerId={}",
                request.getEventId(), request.getEventName(), request.getEventWinnerId());

        EventOutcome eventOutcome = new EventOutcome(
                request.getEventId(),
                request.getEventName(),
                request.getEventWinnerId()
        );

        kafkaProducerService.publishEventOutcome(eventOutcome);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Event outcome published to Kafka");
        response.put("data", eventOutcome);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Betting Settlement Service");
        return ResponseEntity.ok(response);
    }
}