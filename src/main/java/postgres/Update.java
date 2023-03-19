package postgres;

import java.sql.*;

public class Update {
    private Connection connect() throws SQLException {
        Connection conn = null;
        String url = "jdbc:mariadb://localhost:3306/somedb";
        String user = "someuser";
        String password = "somepass";
        conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    public void update() throws SQLException {
        String sql = "INSERT into Users (name, phone) values " +
                "('Petya', '89372635')," +
                "('Vasya', '757849300')," +
        "('Katya', null);";
        Connection conn = this.connect();
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }
}
