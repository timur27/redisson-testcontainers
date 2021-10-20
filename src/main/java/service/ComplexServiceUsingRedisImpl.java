package service;

import config.RedissonLocalConfig;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import java.util.List;
import java.util.UUID;

public class ComplexServiceUsingRedisImpl implements ComplexServiceUsingRedis{
    RedissonClient redisson;
    RMap<UUID, String> userNames;

    public ComplexServiceUsingRedisImpl(RedissonClient redissonClient) {
        this.redisson = redissonClient;
        redisson.getMap("userNames");
    }

    public void addFor(UUID userId, String userName) {
        if (userNames.get(userId) != null) {
            System.out.println("User addded already");
        }
        userNames.put(userId, userName);
    }

    public String getFor(UUID userId) {
        if (userNames.get(userId) == null) {
            System.out.println(String.format("No user data present for the user with id %s", userId));
        }
        return userNames.get(userId);
    }

    public List<String> getAllNames() {
        return userNames.values().stream().toList();
    }

}
