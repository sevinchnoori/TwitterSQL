DROP SCHEMA IF EXISTS followstweet;

CREATE SCHEMA IF NOT EXISTS followstweet;

USE followstweet;

DROP TABLE IF EXISTS tweet;

CREATE TABLE IF NOT EXISTS tweet (
tweet_id INT PRIMARY KEY AUTO_INCREMENT,
user_id INT,
tweet_ts DATETIME,
tweet_text VARCHAR(140)
);

DROP TABLE IF EXISTS foll;

CREATE TABLE IF NOT EXISTS foll (
user_id INT,
follows_id INT
);
