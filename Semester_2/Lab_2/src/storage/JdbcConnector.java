package storage;

import logger.LoggerManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnector {
    private static Connection connection;
    private static Logger logger = LoggerManager.getLogger(JdbcConnector.class.getName());

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            Properties prop;
            try (InputStream input = JdbcConnector.class.getClassLoader().getResourceAsStream("storage/database.properties")) {
                prop = new Properties();
                prop.load(input);
            } catch (Exception e) {
                System.out.println("Failed to open db properties");
                logger.fatal(e);
                return null;
            }

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.username");
            String password = prop.getProperty("db.password");

            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to PostgreSQL database successfully established!");
        }
        return connection;
    }
}
