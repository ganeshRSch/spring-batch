DROP TABLE tradedata IF EXISTS;

CREATE TABLE tradedata  (
    trade_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    trade_name VARCHAR(20),
    trade_value VARCHAR(20)
);