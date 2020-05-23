package com.github.calvincodes.database.handler;

public interface DatabaseHandler {

    void connect();

    boolean setKeyIfNotExist(String key, String value, Long ttlInSeconds);

    void disconnect();
}
