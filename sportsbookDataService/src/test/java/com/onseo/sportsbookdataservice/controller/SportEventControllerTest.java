package com.onseo.sportsbookdataservice.controller;

import com.onseo.sportsbookdataservice.model.SportEvent;
import com.onseo.sportsbookdataservice.service.SportEventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SportEventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SportEventService service;

    @Test
    public void testUpdateEvent() throws Exception {
        SportEvent event = new SportEvent();
        event.setId("1");
        event.setHomeTeam("Home");
        event.setAwayTeam("Away");
        event.setDescription("Some match");
        event.setStartTime(Instant.now());
        event.setSport("Football");
        event.setCountry("Country");
        event.setCompetition("Competition");
        event.setSettled(false);
        event.setMarkets(new ArrayList<>());

        when(service.updateEvent(any(SportEvent.class))).thenReturn(event);

        mockMvc.perform(put("/api/sport-events", event)
                        .contentType("application/json")
                        .content("{\n" +
                                "\"id\": \"1\",\n" +
                                "\"homeTeam\": \"Home\",\n" +
                                "\"awayTeam\": \"Away\",\n" +
                                "\"description\": \"Some match\",\n" +
                                "\"startTime\": \"+0000-0-00T00:00:00Z\",\n" +
                                "\"sport\": \"Football\",\n" +
                                "\"country\": \"Country\",\n" +
                                "\"competition\": \"Competition\",\n" +
                                "\"settled\": false,\n" +
                                "\"markets\": []\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "\"id\": \"1\",\n" +
                        "\"homeTeam\": \"Home\",\n" +
                        "\"awayTeam\": \"Away\",\n" +
                        "\"description\": \"Some match\",\n" +
                        "\"startTime\": \"+0000-0-00T00:00:00Z\",\n" +
                        "\"sport\": \"Football\",\n" +
                        "\"country\": \"Country\",\n" +
                        "\"competition\": \"Competition\",\n" +
                        "\"settled\": false,\n" +
                        "\"markets\": []\n" +
                        "}"));
    }
}