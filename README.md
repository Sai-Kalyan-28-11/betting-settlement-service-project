
Step1:Go to this location https://github.com/Sai-Kalyan-28-11

Under repositories, we can find **betting-settlement-service-project**
Please import this project into your system.
Step2: Open it from any IDE
Step3: Run command:  mvn clean install
Step4: Run command: mvn spring-boot:run
Step5: http://localhost:8081/h2-console/
Step6: Run the insert command:

INSERT INTO BETS (user_id, event_id, event_market_id, event_winner_id, bet_amount, settled)
VALUES
(1001, 100, 1, 5001, 50.00, FALSE),
(1002, 100, 1, 5002, 75.00, FALSE),
(1003, 100, 1, 5001, 100.00, FALSE),
(1004, 100, 2, 5003, 25.00, FALSE),
(1005, 101, 1, 6001, 150.00, FALSE),
(1006, 101, 1, 6002, 200.00, FALSE);


Step7: Run this post command from PostMan
http://localhost:8081/api/events/outcomes
{
"eventId": 100,
"eventName": "Championship Final",
"eventWinnerId": 5001
}


Step8: You will see the below output in the terminal

2025-11-27T10:40:50.451+05:30  INFO 18543 --- [betting-settlement-service] [ntainer#0-0-C-1] c.b.s.service.BetMatchingService         : Found 4 bets to settle for eventId: 100
2025-11-27T10:40:50.454+05:30  INFO 18543 --- [betting-settlement-service] [ntainer#0-0-C-1] c.b.s.service.RocketMQProducerService    : RocketMQ Producer [MOCK] - Sending to topic 'bet-settlements': {"betId":1,"userId":1001,"eventId":100,"eventWinnerId":5001,"betEventWinnerId":5001,"betAmount":50.00,"isWinner":true,"payout":100.000}
2025-11-27T10:40:50.454+05:30  INFO 18543 --- [betting-settlement-service] [ntainer#0-0-C-1] c.b.s.service.RocketMQProducerService    : Settlement Details: betId=1, userId=1001, isWinner=true, payout=100.000
2025-11-27T10:40:50.458+05:30  INFO 18543 --- [betting-settlement-service] [ntainer#0-0-C-1] c.b.s.service.BetMatchingService         : Bet marked as settled: betId=1, userId=1001, isWinner=true
2025-11-27T10:40:50.458+05:30  INFO 18543 --- [betting-settlement-service] [ntainer#0-0-C-1] c.b.s.service.RocketMQProducerService    : RocketMQ Producer [MOCK] - Sending to topic 'bet-settlements': {"betId":2,"userId":1002,"eventId":100,"eventWinnerId":5001,"betEventWinnerId":5002,"betAmount":75.00,"isWinner":false,"payout":0}
2025-11-27T10:40:50.458+05:30  INFO 18543 --- [betting-settlement-service] [ntainer#0-0-C-1] c.b.s.service.RocketMQProducerService    : Settlement Details: betId=2, userId=1002, isWinner=false, payout=0
2025-11-27T10:40:50.458+05:30  INFO 18543 --- [betting-settlement-service] [ntainer#0-0-C-1] c.b.s.service.BetMatchingService         : Bet marked as settled: betId=2, userId=1002, isWinner=false
2025-11-27T10:40:50.458+05:30  INFO 18543 --- [betting-settlement-service] [ntainer#0-0-C-1] c.b.s.service.RocketMQProducerService    : RocketMQ Producer [MOCK] - Sending to topic 'bet-settlements': {"betId":3,"userId":1003,"eventId":100,"eventWinnerId":5001,"betEventWinnerId":5001,"betAmount":100.00,"isWinner":true,"payout":200.000}
2025-11-27T10:40:50.458+05:30  INFO 18543 --- [betting-settlement-service] [ntainer#0-0-C-1] c.b.s.service.RocketMQProducerService    : Settlement Details: betId=3, userId=1003, isWinner=true, payout=200.000
2025-11-27T10:40:50.458+05:30  INFO 18543 --- [betting-settlement-service] [ntainer#0-0-C-1] c.b.s.service.BetMatchingService         : Bet marked as settled: betId=3, userId=1003, isWinner=true
2025-11-27T10:40:50.458+05:30  INFO 18543 --- [betting-settlement-service] [ntainer#0-0-C-1] c.b.s.service.RocketMQProducerService    : RocketMQ Producer [MOCK] - Sending to topic 'bet-settlements': {"betId":4,"userId":1004,"eventId":100,"eventWinnerId":5001,"betEventWinnerId":5003,"betAmount":25.00,"isWinner":false,"payout":0}
2025-11-27T10:40:50.458+05:30  INFO 18543 --- [betting-settlement-service] [ntainer#0-0-C-1] c.b.s.service.RocketMQProducerService    : Settlement Details: betId=4, userId=1004, isWinner=false, payout=0
2025-11-27T10:40:50.458+05:30  INFO 18543 --- [betting-settlement-service] [ntainer#0-0-C-1] c.b.s.service.BetMatchingService         : Bet marked as settled: betId=4, userId=1004, isWinner=false

