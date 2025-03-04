package com.onseo.sportsbookdataservice.service;

import com.hazelcast.map.IMap;
import com.onseo.sportsbookdataservice.model.SportEvent;
import com.onseo.sportsbookdataservice.model.SportEventHistory;
import com.onseo.sportsbookdataservice.repository.SportEventHistoryRepository;
import com.onseo.sportsbookdataservice.repository.SportEventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SportEventServiceImplTest {

    @Mock
    private SportEventRepository sportEventRepository;

    @Mock
    private SportEventHistoryRepository sportEventHistoryRepository;

    @Mock
    private IMap<String, Optional<SportEvent>> sportEventMap;

    @Mock
    private IMap<String, List<SportEvent>> sportsEventMap;

    @InjectMocks
    private SportEventServiceImpl sportEventService;

    @Test
    void testUpdateEventSuccess() {
        SportEvent sampleEvent = new SportEvent();
        sampleEvent.setId("sample id");
        sampleEvent.setDescription("sample description");
        sampleEvent.setHomeTeam("sample home team");
        sampleEvent.setAwayTeam("sample away team");
        sampleEvent.setStartTime(Instant.now());
        sampleEvent.setSport("sample sport");
        sampleEvent.setCountry("sample country");
        sampleEvent.setCompetition("sample competition");
        sampleEvent.setSettled(false);

        when(sportEventRepository.save(sampleEvent)).thenReturn(sampleEvent);
        when(sportEventHistoryRepository.save(any(SportEventHistory.class))).thenReturn(new SportEventHistory());

        SportEvent result = sportEventService.updateEvent(sampleEvent);

        assertEquals(sampleEvent.getId(), result.getId());
        verify(sportEventRepository).save(any(SportEvent.class));
        verify(sportEventHistoryRepository).save(any(SportEventHistory.class));
    }

    @Test
    void testUpdateEventFailNoId() {
        SportEvent sampleEvent = new SportEvent();

        assertThrows(IllegalArgumentException.class, () -> {
            sportEventService.updateEvent(sampleEvent);
        });
    }
}