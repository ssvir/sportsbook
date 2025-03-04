package com.onseo.sportsbookretrievingservice.service;

import com.onseo.sportsbookretrievingservice.model.SportEvent;
import com.onseo.sportsbookretrievingservice.repository.SportEventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DataJpaTest
public class SportEventServiceImplTest {

    @Autowired
    private SportEventServiceImpl sportEventService;

    @MockitoBean
    private SportEventRepository sportEventRepository;

    @Test
    void getNonSettledEvents_returnsCorrectSportEvents() {
        // Given
        SportEvent sportEvent = new SportEvent();
        sportEvent.setId("1");
        sportEvent.setSettled(false);
        sportEvent.setSport("Football");
        sportEvent.setStartTime(Instant.now());
        sportEvent.setHomeTeam("Home");
        sportEvent.setAwayTeam("Away");

        when(sportEventRepository.findBySportAndSettledFalseOrderByStartTimeWithoutMarkets(anyString())).thenReturn(Collections.singletonList(sportEvent));

        // When
        List<SportEvent> actualSportEvents = sportEventService.getNonSettledEvents("Football");

        // Then
        assertEquals(1, actualSportEvents.size());
        SportEvent actualSportEvent = actualSportEvents.get(0);
        assertEquals("1", actualSportEvent.getId());
        assertEquals("Football", actualSportEvent.getSport());
        assertEquals("Home", actualSportEvent.getHomeTeam());
        assertEquals("Away", actualSportEvent.getAwayTeam());
    }
}