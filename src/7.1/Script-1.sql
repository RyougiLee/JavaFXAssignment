DROP DATABASE IF EXISTS converter;
CREATE DATABASE converter;
Use converter;

drop table if exists CURRENCY;

CREATE TABLE CURRENCY(
	id INT NOT NULL AUTO_INCREMENT,
	currency_name VARCHAR(20) NOT NULL,
	abbr_name VARCHAR(10) NOT NULL,
	rate_to_usd FLOAT NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO CURRENCY(currency_name, abbr_name, rate_to_usd) VALUES
	('Japanese Yen', 'JPY', 0.0067),
	('British Pound', 'GBP', 1.34),
	('Euro', 'EUR', 1.17),
	('Chinese Yuan', 'CNY', 0.14),
	('New Taiwan Dollar', 'NTD', 0.033),
	('Hong Kong Dollar', 'HKD', 0.13),
	('Australian dollar', 'AUD', 0.65),
	('Canadian Dollar', 'AUD', 0.72);

DROP USER IF EXISTS 'appuser'@'localhost';

CREATE USER 'appuser'@'localhost' IDENTIFIED BY 'test';

GRANT SELECT, INSERT, UPDATE, DELETE ON converter.* TO 'appuser'@'localhost';


