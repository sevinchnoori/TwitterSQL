import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class twitterImpl implements twitter {

    DBUtils dbu;

    @Override
    public void setupDB() {
        dbu.fileToDB();
    }

    @Override
    public void postTweet(tweet t) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String now = LocalDateTime.now().format(formatter);

        String sqlInsert = "INSERT INTO tweet (user_id, tweet_ts, tweet_text) VALUES" +
                " (" + t.twtUID() + ", '" + now + "', '" + t.twtText() + "')";

        dbu.insertDB(sqlInsert);

    }

    @Override
    public List<tweet> getTimeline(Integer user_id) throws SQLException {
        List<tweet> recents = new ArrayList<tweet>();

        String sqlInsert = "SELECT tweet_id, tweet.user_id, tweet_ts, tweet_text FROM foll JOIN tweet ON " +
                "tweet.user_id = foll.follows_id WHERE foll.user_id = " + user_id + " ORDER BY tweet_ts DESC LIMIT 10";
      
        for (int i = 1; i <= 10; i++) {
            tweet twt = dbu.getTweet(i, sqlInsert);
            recents.add(twt);
        }

        return recents;
    }

    public void printTimeline(List<tweet> listTwt) {
        for (tweet t :listTwt) {
            System.out.print(t.twtToString());
        }
    }

    @Override
    public void authenticate(String url, String user, String pass) {
        dbu = new DBUtils(url, user, pass);
    }

    @Override
    public void closeConnection() {
        dbu.closeConnection();
    }

}
