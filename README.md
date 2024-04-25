# TwitterSQL

## Intro

When Twitter first started out, its engineers used MySQL as a backend relational database. 

There are two key operations of twitter that my code supports:

a) Users post tweets. We are ignoring hashtags. 

b) Users retrieve all of the tweets posted by all users followed by that user. 
This set of tweets – which the user sees when he or she opens up the twitter app 
on a smart phone – is known as the user’s home timeline.

## Database Initialization

TWEET – The tweets posted by users
tweet_id INT (PK)
user_id INT (FK)
tweet_ts DATETIME
tweet_text VARCHAR(140)

FOLLOWS – Who follows whom. The user “user_id” follows the user “follows_id”
user_id INT (FK)
follows_id INT (FK)


follows.csv and tweets.csv loaded into the database (too large to include in this repository)
