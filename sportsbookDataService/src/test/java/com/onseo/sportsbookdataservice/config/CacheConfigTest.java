package com.onseo.sportsbookdataservice.config;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.onseo.sportsbookdataservice.model.SportEvent;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
public class CacheConfigTest {

    @Autowired
    IMap<String, Optional<SportEvent>> sportEventMap;

    @MockitoBean
    HazelcastInstance mockHazelcastInstance;

    @Test
    public void testSportEventMap() {
        Mockito.doReturn(sportEventMap).when(mockHazelcastInstance).getMap("sportEvent");

        SportEvent mockEvent = new SportEvent();
        Optional<SportEvent> wrappedEvent = Optional.of(mockEvent);

        sportEventMap.put("testEvent", wrappedEvent);
        Optional<SportEvent> resultEvent = sportEventMap.get("testEvent");

        assertSame(wrappedEvent, resultEvent, "Should return the same sport event object");

        sportEventMap.remove("testEvent");
        assertNull(sportEventMap.get("testEvent"), "Should return null after sport event has been removed");
    }
}