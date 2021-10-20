package config;

import org.redisson.api.RedissonClient;

public interface RedissonConfig {
    RedissonClient redissonClient(String address, int port);
}
