package service;

import config.RedissonConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class ComplexServiceUsingRedisImplTest {

    @Container
    public GenericContainer redis = new GenericContainer(DockerImageName.parse("redis:5.0.3-alpine"))
            .withExposedPorts(6379);

    private ComplexServiceUsingRedisImpl underTest;

    @BeforeEach
    void setUp() {
        final var redisson = new RedissonTestConfig().redissonClient(redis.getHost(), redis.getFirstMappedPort());
        underTest = new ComplexServiceUsingRedisImpl(redisson);
    }

    @Test
    void addFor() {
        underTest.addFor(UUID.randomUUID(), "testUserName");

        assertThat(underTest.getAllNames()).isNotEmpty();
    }

    @Test
    void getFor() {
    }

    @org.junit.jupiter.api.Test
    void getAllNames() {
    }

    class RedissonTestConfig implements RedissonConfig {
        private Config config(String address, int port) {
            Config config = new Config();
            config.useSingleServer()
                    .setAddress(address + ":" + port);
            return config;
        }

        public RedissonClient redissonClient(String address, int port) {
            return Redisson.create(config(address, port));
        }
    }
}