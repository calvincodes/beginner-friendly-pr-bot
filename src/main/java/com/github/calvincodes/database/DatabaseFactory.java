package com.github.calvincodes.database;

public class DatabaseFactory {

    public static DatabaseActions getDatabaseActions() {
        String databaseType = System.getenv("FOSC_DATABASE_TYPE");
        if (databaseType.equalsIgnoreCase("redis")) {
            return new RedisDatabaseActions();
        } else {
            return new InMemoryDatabaseActions();
        }
    }
}
