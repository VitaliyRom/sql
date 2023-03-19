package mysql;

import java.sql.*;

public class Select {

    private Connection connect() throws SQLException {
        Connection conn = null;
        String url = "jdbc:mariadb://localhost:3306/somedb";
        String user = "someuser";
        String password = "somepass";
        conn = DriverManager.getConnection(url, user, password);
        conn.createStatement().execute("CREATE table if not exists Users (" +
                "id int primary key auto_increment," +
                "name varchar(20) not null," +
                "phone varchar(20) default null); \n");
        System.out.println("Table was exist");
        conn.createStatement().execute("INSERT into Users (name, phone) values" +
                "('Petya', '89372635')," +
                "('Vasya', '757849300')," +
                "('Katya', null);");

        System.out.println("Table was created");
        return conn;
    }

    public void selectAll() {
        String sql = "select * from Users";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (true) {
                try {
                    if (!rs.next()) break;
                } catch (SQLException e) {

                }
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("phone"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Select app = new Select();
        app.selectAll();
    }
}

