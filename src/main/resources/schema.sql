
CREATE TABLE IF NOT EXISTS BETS (
    bet_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    event_id BIGINT NOT NULL,
    event_market_id BIGINT NOT NULL,
    event_winner_id BIGINT NOT NULL,
    bet_amount DECIMAL(10,2) NOT NULL,
    settled BOOLEAN DEFAULT FALSE
);
