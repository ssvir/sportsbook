package com.onseo.sportsbookretrievingservice.service;

import com.onseo.sportsbookretrievingservice.model.SportEvent;
import com.onseo.sportsbookretrievingservice.repository.SportEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SportEventServiceImpl implements SportEventService {
    @Autowired
    private SportEventRepository repository;

    @Cacheable(value = "sportEvents", key = "#sport")
    public List<SportEvent> getNonSettledEvents(String sport) {
        return repository.findBySportAndSettledFalseOrderByStartTimeWithoutMarkets(sport);
    }

    @Cacheable(value = "sportEvent", key = "#id")
    public Optional<SportEvent> getEventById(String id) {
        return repository.findById(id);
    }
}
