package utils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/shop?useUnicode=true" +
            "&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "12345";
    private static final Logger logger = Logger.getLogger(DBConnection.class);

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER).newInstance();
            return DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (InstantiationException | IllegalAccessException
                | ClassNotFoundException | SQLException e) {
            logger.error("SQL exception: " + e);
        }
        return null;
    }
}
