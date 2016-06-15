import com.theironyard.Main;
import com.theironyard.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
        User alice = new User(1, "alice", "123lane", "@email");
        Connection conn = startConnection();
        Main.insertUser(conn, alice);
        conn.close();
        assertTrue(alice != null);
    }

}
