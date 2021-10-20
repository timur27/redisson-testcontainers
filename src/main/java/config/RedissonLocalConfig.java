package config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonLocalConfig implements RedissonConfig {
    private Config config(String address, int port) {
        Config config = new Config();
        config.useSingleServer()
                        .setAddress(address + ":" + port);
        return config;
    }

    @Override
    public RedissonClient redissonClient(String address, int port) {
        return Redisson.create(config(address, port));
    }
}
