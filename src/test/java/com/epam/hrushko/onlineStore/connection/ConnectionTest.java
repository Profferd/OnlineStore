package com.epam.hrushko.onlineStore.connection;

import com.epam.hrushko.onlinestore.connection.ConnectionPool;
import com.epam.hrushko.onlinestore.exceptions.ConnectionException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConnectionTest {
    @BeforeAll
    static void initializeConnectionPool() throws ConnectionException {
        ConnectionPool.getInstance().initialize();
    }

    @Test
    void testGetInstance_ShouldReturnTheSameClass_Always() {
        ConnectionPool poolFirst = ConnectionPool.getInstance();
        ConnectionPool poolSecond = ConnectionPool.getInstance();

        assertEquals(poolFirst, poolSecond);
    }

    @Test
    void testGetConnection_ShouldReturnTrue_WhenConnectionValidTenSeconds() throws SQLException, ConnectionException {
        assertTrue(ConnectionPool.getInstance().getConnection().isValid(10));
    }
}
