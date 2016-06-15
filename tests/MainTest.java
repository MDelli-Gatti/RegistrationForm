import com.theironyard.Main;
import com.theironyard.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * Created by michaeldelli-gatti on 6/15/16.
 */
public class MainTest {
    public Connection startConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");
        Main.createTables(conn);
        return conn;
    }

    @Test
    public void testInsertUser() throws SQLException {
        User alice = new User(1, "Alice", "123lane", "@email");
        Connection conn = startConnection();
        Main.insertUser(conn, alice);
        ArrayList user = Main.selectUsers(conn);
        conn.close();
        assertTrue(user != null);
    }

    @Test
    public void testUpdateUsers() throws SQLException {
        User alice = new User(1, "Alice", "123lane", "A@email");
        Connection conn = startConnection();
        Main.insertUser(conn, alice);
        User bob = new User(1, "Bob", "321road", "B@email");
        Main.updateUser(conn, bob);
        ArrayList<User> testUsers = Main.selectUsers(conn);
        User testUser = testUsers.get(0);
        conn.close();
        assertTrue(testUser.getUsername().equals("Bob"));
    }

    @Test
    public void testDeleteUser() throws SQLException {
        Connection conn = startConnection();
        User alice = new User(1, "Alice", "123lane", "@email");
        User bob = new User(2, "Bob", "321road", "B@email");
        Main.insertUser(conn, alice);
        Main.insertUser(conn, bob);
        ArrayList users = Main.selectUsers(conn);
        Main.deleteUser(conn, 2);
        users = Main.selectUsers(conn);
        assertTrue(users.size() == 1);
    }
}
