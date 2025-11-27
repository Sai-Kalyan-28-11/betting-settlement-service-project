package com.betting.settlement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class BettingSettlementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BettingSettlementServiceApplication.class, args);
	}

}
