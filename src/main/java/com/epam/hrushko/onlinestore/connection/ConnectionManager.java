package com.epam.hrushko.onlinestore.connection;

import com.epam.hrushko.onlinestore.exceptions.ConnectionException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Connection manager which contain all information about database and connecting to it
 */
public class ConnectionManager {
    private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class);
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_DRIVER = "db.driver";

    /**
     * Creating connection to database
     * @param db properties to connect to database
     * @return connection
     * @throws ConnectionException
     */
    static MyConnection createConnection(Properties db) throws ConnectionException {
        MyConnection connection = null;
        try {
            String dbUrl = db.getProperty(DB_URL);
            String dbUser = db.getProperty(DB_USER);
            String dbPassword = db.getProperty(DB_PASSWORD);
            Class.forName(db.getProperty(DB_DRIVER));
            connection = new MyConnection(DriverManager.getConnection(dbUrl, dbUser, dbPassword));
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ConnectionException(e.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ConnectionException(e.getMessage());
        }
        return connection;
    }
}
