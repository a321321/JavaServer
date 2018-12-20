package shoutingMTserver;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class DBHelper {
    private Connection conn;

    public DBHelper(Array keys, Array values) {

    }

    public void connect() {
        String dbURL = "jdbc:mysql://localhost:3306/sampledb";
        String username = "root";
        String password = "";

        try {

            conn = DriverManager.getConnection(dbURL, username, password);

            if (conn != null) {
                System.out.println("Connected");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void disconnect() throws SQLException {
        conn.close();
    }

    public void insert(String stn, String date, String time, float temp, float demp, float stp, float slp, float visib, float wdsp, float prcp, float sndp, int frshtt, float cldc, int wnddir) throws SQLException {
        String sql = "INSERT INTO Users (username, password, fullname, email) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, "bill");
        statement.setString(2, "secretpass");
        statement.setString(3, "Bill Gates");
        statement.setString(4, "bill.gates@microsoft.com");

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new user was inserted successfully!");
        }
    }

    public String read(String input) {
        int start = input.indexOf(">");
        int end = input.indexOf("<");

        return input.substring(start, end);
    }
}
