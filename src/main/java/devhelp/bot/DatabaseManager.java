package devhelp.bot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String URL = "jdbc:sqlite:data/bot_database.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

}
