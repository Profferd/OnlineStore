package com.epam.hrushko.onlinestore.connection;

import com.epam.hrushko.onlinestore.exceptions.ConnectionException;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_DRIVER = "db.driver";

    static MyConnection createConnection(Properties db) throws ConnectionException {
        MyConnection connection = null;
        try {
            String dbUrl = db.getProperty(DB_URL);
            String dbUser = db.getProperty(DB_USER);
            String dbPassword = db.getProperty(DB_PASSWORD);
            Class.forName(db.getProperty(DB_DRIVER));
            connection = new MyConnection(DriverManager.getConnection(dbUrl, dbUser, dbPassword));
        } catch (SQLException e) {
            //logger 1
            throw new ConnectionException(e.getMessage());
        } catch (ClassNotFoundException e) {
            //logger 2
            throw new ConnectionException(e.getMessage());
        }
        return connection;
    }
}
