package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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


