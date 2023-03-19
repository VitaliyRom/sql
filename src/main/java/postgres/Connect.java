package postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static void connect() throws SQLException {
        Connection conn = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/postdb";
            String user = "someuser";
            String password = "somepass";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to mariadb has been established");
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

