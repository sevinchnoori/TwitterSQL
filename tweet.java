import java.time.LocalDateTime;

// class to represent a tweet object
public class tweet {
    private int tweetID;
    private int userID;
    private LocalDateTime tweet_ts;
    private String text;

    // constructor used to set a new tweet
    public tweet(int tid, int uid, LocalDateTime ts, String txt) {
        this.tweetID = tid;
        this.userID = uid;
        this.tweet_ts = ts;
        this.text = txt;
    }

    // constructor to get a tweet from the file
    public tweet(int uid, String txt) {
        this.tweetID = -1;
        this.userID = uid;
        this.tweet_ts = LocalDateTime.now();
        this.text = txt;
    }

    // returns a tweet as a String
    public String twtToString() {
        return this.tweetID + " " + this.userID + " " +  this.tweet_ts + " " + this.text + "\n";
    }

    // getters for tweet object
    public int twtUID() {
        return this.userID;
    }

    public String twtText() {
        return this.text;
    }

}
