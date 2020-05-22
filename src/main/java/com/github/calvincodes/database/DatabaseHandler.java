package com.github.calvincodes.database;

public interface DatabaseHandler {

    public void connect();

    public boolean setKeyIfNotExist(String key, String value, Long ttlInSeconds);

    public void disconnect();
}
