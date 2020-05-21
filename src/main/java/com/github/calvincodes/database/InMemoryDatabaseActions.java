package com.github.calvincodes.database;

public class InMemoryDatabaseActions implements DatabaseActions {

    @Override
    public void connect() {

    }

    @Override
    public boolean setKey(String key, String value, Long ttlInSeconds) {
        return false;
    }

    @Override
    public void disconnect() {

    }
}
