package com.github.calvincodes.database;

public class DatabaseFactory {

    public static DatabaseHandler getDatabaseActions() {
        String databaseType = System.getenv("FOSC_DATABASE_TYPE");
        if ("REDIS".equalsIgnoreCase(databaseType)) {
            return new RedisDatabaseHandler();
        } else {
            return new InMemoryDatabaseHandler();
        }
    }
}
