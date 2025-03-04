package com.onseo.sportsbookretrievingservice.controller;

import com.onseo.sportsbookretrievingservice.model.SportEvent;
import com.onseo.sportsbookretrievingservice.service.SportEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/sport-events")
public class SportEventController {
    @Autowired
    private SportEventService service;

    @GetMapping
    public List<SportEvent> getNonSettledEvents(@RequestParam String sport) {
        return service.getNonSettledEvents(sport);
    }

    @GetMapping("/{id}")
    public Optional<SportEvent> getEventById(@PathVariable String id) {
        return service.getEventById(id);
    }
}
