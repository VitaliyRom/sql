package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static void connect() throws SQLException {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:F://Idea Projects//sq.db";
            String user = "someuser";
            String password = "somepass";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to sqlite has been established");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        Connect.connect();
    }
}


