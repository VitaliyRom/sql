package sqlite;

import java.sql.*;

public class Update {
    private Connection connect() throws SQLException {
        Connection conn = null;
        String url = "jdbc:sqlite:F://Idea Projects//sq.db";
        String user = "someuser";
        String password = "somepass";
        conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    public void update() throws SQLException {
        String sql = "UPDATE Users \n" +
                "set name = 'Ne Petya', phone='485785949'\n" +
                "where id = 3;";
        Connection conn = this.connect();
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }

    public static void main(String[] args) throws SQLException {
        Update app = new Update();
        app.update();
    }
}
