package com.github.calvincodes.database;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisConnection;
import com.lambdaworks.redis.protocol.SetArgs;

public class RedisDatabaseActions implements DatabaseActions {

    private RedisClient redisClient = null;
    private RedisConnection<String, String> redisConnection = null;

    private volatile Boolean IS_CONNECTION_ESTABLISHED = false;

    @Override
    public void connect() {
        synchronized (IS_CONNECTION_ESTABLISHED) {
            if (!IS_CONNECTION_ESTABLISHED) {
                // TODO: Create separate config file
                String redisHost = System.getenv("FOSC_REDIS_HOST");
                int redisPort = Integer.parseInt(System.getenv("FOSC_REDIS_PORT"));
                redisClient = new RedisClient(redisHost, redisPort);
                redisConnection = redisClient.connect();
                IS_CONNECTION_ESTABLISHED = true;
                System.out.println("Connected to Redis");
            }
        }
    }

    @Override
    public boolean setKey(String key, String value, Long ttlInSeconds) {
        if (IS_CONNECTION_ESTABLISHED) {
            SetArgs setArgs = SetArgs.Builder.nx().ex(ttlInSeconds);
            return "OK".equals(redisConnection.set(key, value, setArgs));
        }
        throw new RuntimeException("No Redis Database connection");
    }

    @Override
    public void disconnect() {
        synchronized (IS_CONNECTION_ESTABLISHED) {
            if (IS_CONNECTION_ESTABLISHED) {
                redisConnection.close();
                redisClient.shutdown();
                IS_CONNECTION_ESTABLISHED = false;
                System.out.println("Closed Redis connection");
            }
        }
    }
}
