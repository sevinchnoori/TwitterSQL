import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

import java.sql.*;


public class DBUtils {


    private String url;
    private String user;
    private String pass;
    private Connection c = null;


    public DBUtils(String url, String user, String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.c = getConnection();
    }

    /**
     * Connects to the database
     * @return a connection to the database
     */
    public Connection getConnection() {
        if (c == null) {
            try {
                c = DriverManager.getConnection(url, user, pass);
                return c;
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        return c;
    }

    /**
     * Closes connection to the database
     */
    public void closeConnection() {
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes a SQL query to insert tweet data into the tweet table.
     */public void insertDB(String SQLStatement) {

        int key = -1;
        try {
            Connection c = getConnection();
            Statement query = c.createStatement();

            query.executeUpdate(SQLStatement, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = query.getGeneratedKeys();
            if (rs.next()) key = rs.getInt(1);

            rs.close();
            query.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates connection to DB and inserts data into follow table
     */
    public void fileToDB() {
        String line = "";

        try {
            Connection con = getConnection();
            Statement query = con.createStatement();
            String sqlInsert = "";
            follows newF = new follows();

            try {
                BufferedReader br = new BufferedReader(new FileReader("/Users/sevinchnoori/Downloads/dshw1/src/follows.csv"));
                br.readLine(); // reads 1st line to skip

                while ((line = br.readLine()) != null) {
                    String[] row = line.split(","); // creates a String array of the row of the CSV split by ,

                    // sets parameters of follows object
                    newF.setFllwUID(Integer.parseInt(row[0]));
                    newF.setFllwFID(Integer.parseInt(row[1]));

                    // SQL to insert data into the folls table using the follows object
                    sqlInsert = "INSERT INTO foll" + " VALUES (" + newF.fllwUID() + ", '" + newF.fllwFID() + "')";
                    query.executeUpdate(sqlInsert);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            query.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Retrieves a tweet from the resultant table of a SQL query
     * @param index row of the tweet to retrieve
     * @param SQLStatement SQL Statement to retrieve the timeline of the random user
     * @return a tweet
     */
    public tweet getTweet(int index, String SQLStatement) {
        tweet newT = null;
        try {
            Connection c = getConnection();
            Statement query = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = query.executeQuery(SQLStatement);

            // row of resultant table to retrieve values from
            rs.absolute(index);

            // individual values to set to tweet object
            int tid = rs.getInt(1);
            int uid = rs.getInt(2);
            LocalDateTime ts = rs.getObject(3, LocalDateTime.class);
            String txt = rs.getString(4);

            // creates a tweet object from row data
            newT = new tweet(tid, uid, ts, txt);
            query.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newT;

    }
}

