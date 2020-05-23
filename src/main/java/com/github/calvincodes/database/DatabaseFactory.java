package com.github.calvincodes.database;

public class DatabaseFactory {

    public static DatabaseHandler getDatabaseActions() {
        String environment = System.getenv("FOSC_ENVIRONMENT");
        if ("PROD".equalsIgnoreCase(environment)) {
            return new RedisDatabaseHandler();
        } else {
            return new InMemoryDatabaseHandler();
        }
    }
}
