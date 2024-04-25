import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;
public class FollowsTweet {

    private static twitter t = new twitterImpl();


    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/followstweet?serverTimezone=EST5EDT";
        String user = System.getenv("FOLLOWSTWEET_USER");
        String pass = System.getenv("FOLLOWSTWEET_PASS");


        t.authenticate(url, user, pass);

        // sets up the follows table
        t.setupDB();

        String line = "";

        // timer for posting to tweet table
        long startTime = 0;
        long endTime = 0;
        tweet newT = null;

        // reading from tweet.csv file
        try {
            startTime = System.currentTimeMillis();
            BufferedReader br = new BufferedReader(new FileReader("/Users/sevinchnoori/Downloads/dshw1/src/tweet.csv"));
            br.readLine(); // reads 1st line to skip it

            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");

                // creates new tweet object from row to be posted
                newT = new tweet(Integer.parseInt(row[0]), row[1]);
                t.postTweet(newT); // posts tweet into database
            }

            endTime = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long totalTimeForTweets = endTime - startTime; // total time to post 1 mil tweets
        System.out.println("It took " + totalTimeForTweets + " seconds to post 1 million tweets which is " + (long) 1000000/totalTimeForTweets + " tweets per second\n");

        // timer for timeline retrieval
        long startTimeline = 0;
        long endTimeline = 0;
        int times = 1;

        startTimeline = System.currentTimeMillis();
        int randomNum = 0;
        while (times <= 10) { // loops 10 times
            // generates random user within user_id range
            randomNum = ThreadLocalRandom.current().nextInt(0, 10000);
            t.getTimeline(randomNum);
            times += 1;
        }
        endTimeline = System.currentTimeMillis();

        t.printTimeline(t.getTimeline(2314)); // for performance testing
        long totalTimelineTime = endTimeline - startTimeline;

        System.out.print("It took " + totalTimelineTime + " seconds to get 10 timelines which is " + (long) 10/totalTimelineTime + " timelines per second\n");

        t.closeConnection();

    }
} 
