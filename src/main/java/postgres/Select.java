package postgres;

import models.Contact;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Select {
    public Select() throws SQLException {
    }

    private Connection connect() throws SQLException {
        Connection conn = null;
        String url = "jdbc:postgresql://localhost:5432/postdb";
        String user = "someuser";
        String password = "somepass";
        conn = DriverManager.getConnection(url, user, password);
        conn.createStatement().execute("create table if not exists \"Users\" (" +
                "id int generated always as identity," +
                "name varchar(20) not null," +
                "primary key(id)); \n" +
                "create table if not exists \"Contacts\" (" +
                "id int generated always as identity," +
                "\"customerId\" int," +
                "\"contactName\" varchar(255) not null," +
                "phone varchar(15)," +
                "email varchar(50)," +
                "primary key(id)," +
                "constraint \"fk_Users_Contacts\"" +
             "foreign key(\"customerId\")" +
             "references \"Users\"(id));"
);

        System.out.println("Table was created");

        return conn;
    }

    public void selectAll() {
        String sql = "select u.id as \"uId\", u.name as \"uName\", c.id as \"contactId\", " +
                "c.\"contactName\", c.phone, c.email from \"Users\" u left join \"Contacts\" c" +
                " on u.id = c.\"customerId\" order by \"uId\";";
        List<User> users = new ArrayList<>();
        User tmpUser;
        List<Contact> tmpContacts = new ArrayList<>();
        try (Connection conn = this.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Contact con = new Contact(rs.getInt("contactId"), rs.getInt("uId"),
                        rs.getString("contactName"), rs.getString("phone"),
                        rs.getString("email"));
                tmpContacts.add(con);
                tmpUser = new User(rs.getInt("uId"), rs.getString("uName"), tmpContacts);
                users.add(tmpUser);
            }
            System.out.println(users);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException {
        Select app = new Select();
        app.selectAll();
    }
}
