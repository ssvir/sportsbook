package com.onseo.sportsbookretrievingservice.service;

import com.onseo.sportsbookretrievingservice.model.SportEvent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SportEventService {
    List<SportEvent> getNonSettledEvents(String sport);
    Optional<SportEvent> getEventById(String id);
}
