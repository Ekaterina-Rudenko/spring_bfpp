package by.rudenko.spring.database.repository;

import by.rudenko.spring.database.pool.ConnectionPool;

public class UserRepository {
    private final ConnectionPool connectionPool;


    public UserRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}
