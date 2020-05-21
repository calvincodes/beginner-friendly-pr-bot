package com.github.calvincodes.database;

public interface DatabaseActions {

    public void connect();

    public boolean setKey(String key, String value, Long ttlInSeconds);

    public void disconnect();
}
