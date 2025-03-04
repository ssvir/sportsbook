package com.onseo.sportsbookretrievingservice.config;

import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager(HazelcastInstance hazelcastInstance) {
        return new HazelcastCacheManager(hazelcastInstance);
    }

    @Bean
    public Config hazelcastConfig() {
        Config config = Config.load();
        config.getSerializationConfig()
                .getCompactSerializationConfig()
                .addSerializer(new InstantCompactSerializer()); // Ensure correct serializer
        return config;
    }
}
