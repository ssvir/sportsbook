package com.onseo.sportsbookdataservice.service;

import com.onseo.sportsbookdataservice.model.SportEvent;

public interface SportEventService {
    SportEvent createEvent(SportEvent event);

    SportEvent updateEvent(SportEvent event);

    void deleteEvent(String id);
}
