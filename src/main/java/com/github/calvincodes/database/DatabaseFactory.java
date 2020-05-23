package com.github.calvincodes.database;

import com.github.calvincodes.database.handler.DatabaseHandler;
import com.github.calvincodes.database.handler.InMemoryDatabaseHandler;
import com.github.calvincodes.database.handler.RedisDatabaseHandler;

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
