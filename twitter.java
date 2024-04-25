import java.sql.SQLException;
import java.util.List;

// interface used to post a tweet, populate the foll table, and retrieve a timeline
public interface twitter {

    /**
     * Sets up the foll table by inserting data from follows.csv
     */
    void setupDB();


    /**
     * Posts a tweet
     * @param t The tweet
     */
    void postTweet(tweet t);

    /**
     * Retrieves a user's timeline
     * @param user_id The user whose timeline is to be returned
     * @return a list of the 10 most recent tweets on a user's timeline
     */
    List<tweet> getTimeline(Integer user_id) throws SQLException;

    /**
     *
     * @param url
     * @param user
     * @param pass
     */
    void authenticate(String url, String user, String pass);

    /**
     * Closes connection to a database
     */
    void closeConnection();

    /**
     * Prints the timeline
     * @param listTwt list of tweets that are some user's 10 most recent tweets
     */
    void printTimeline(List<tweet> listTwt);
}
