package com.github.calvincodes.database;

import java.util.HashSet;
import java.util.Set;

public class InMemoryDatabaseHandler implements DatabaseHandler {

    private Set<String> inMemoryDB;

    @Override
    public void connect() {
        if (inMemoryDB == null) {
            inMemoryDB = new HashSet<>();
        }
    }

    @Override
    public boolean setKeyIfNotExist(String key, String value, Long ttlInSeconds) {
        if (!inMemoryDB.contains(key)) {
            inMemoryDB.add(key);
            return true;
        }
        return false;
    }

    @Override
    public void disconnect() {
        inMemoryDB = null;
    }
}
