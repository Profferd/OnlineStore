package com.epam.hrushko.onlinestore.connection;

import com.epam.hrushko.onlinestore.exceptions.ConnectionException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String POOL_SIZE = "db.poolsize";
    private static final String DB_CONNECTION_PATH = "connection/db.properties";

    private BlockingQueue<MyConnection> freeConnections;
    private BlockingQueue<MyConnection> takenConnections;
    private static ConnectionPool instance = null;

    public static ConnectionPool getInstance() {
        if (instance==null)
            instance = new ConnectionPool();
        return instance;
    }

    private ConnectionPool() {
    }

    public void initialize() throws ConnectionException {
        try {
            Properties dbProperties = new Properties();
            dbProperties.load(ConnectionManager.class.getClassLoader().getResourceAsStream(DB_CONNECTION_PATH));
            int poolSize = Integer.parseInt(dbProperties.getProperty(POOL_SIZE));
            freeConnections = new ArrayBlockingQueue<>(poolSize);
            takenConnections = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                MyConnection connection = ConnectionManager.createConnection(dbProperties);
                freeConnections.add(connection);
            }
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ConnectionException(e.getMessage(), e);
        }

    }

    public void releaseConnection(MyConnection connection) {
        takenConnections.remove(connection);
        freeConnections.offer(connection);
    }

    public MyConnection getConnection() throws ConnectionException {
        MyConnection connection;
        try {
            connection = freeConnections.take();
            takenConnections.put(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ConnectionException(e.getMessage(), e);
        }
        return connection;
    }

    public void destroy() throws ConnectionException {
        for (MyConnection connection : freeConnections) {
            connection.realClose();
        }
        for (MyConnection connection : takenConnections) {
            connection.realClose();
        }

    }
}
