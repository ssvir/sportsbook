package com.onseo.sportsbookdataservice.config;

import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import com.onseo.sportsbookdataservice.model.SportEvent;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager(HazelcastInstance hazelcastInstance) {
        return new HazelcastCacheManager(hazelcastInstance);
    }

    @Bean
    public IMap<String, Optional<SportEvent>> sportEventMap(HazelcastInstance instance) {
        return instance.getMap("sportEvent");
    }

    @Bean
    public IMap<String, List<SportEvent>> sportsEventMap(HazelcastInstance instance) {
        return instance.getMap("sportsEvent");
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
