package com.onseo.sportsbookretrievingservice.controller;

import com.onseo.sportsbookretrievingservice.model.SportEvent;
import com.onseo.sportsbookretrievingservice.service.SportEventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SportEventControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private SportEventService sportEventService;

    @Test
    void shouldReturnNonSettledSportEvents() throws Exception {
        String sportType = "Football";
        SportEvent event1 = new SportEvent();
        event1.setId("1");
        event1.setSport(sportType);
        event1.setSettled(false);

        SportEvent event2 = new SportEvent();
        event2.setId("2");
        event2.setSport(sportType);
        event2.setSettled(false);

        given(sportEventService.getNonSettledEvents(sportType))
                .willReturn(Arrays.asList(event1, event2));

        mvc.perform(get("/api/sport-events")
                        .param("sport", sportType)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].sport").value(sportType))
                .andExpect(jsonPath("$[0].settled").value(false))
                .andExpect(jsonPath("$[1]").exists())
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].sport").value(sportType))
                .andExpect(jsonPath("$[1].settled").value(false));
    }

    @Test
    void shouldReturnEmptyListIfNoNonSettledSportEventsExist() throws Exception {
        String sportType = "Football";

        given(sportEventService.getNonSettledEvents(sportType)).willReturn(Arrays.asList());

        mvc.perform(get("/api/sport-events")
                        .param("sport", sportType)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
}