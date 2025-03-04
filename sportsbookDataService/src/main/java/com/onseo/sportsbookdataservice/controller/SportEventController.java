package com.onseo.sportsbookdataservice.controller;

import com.onseo.sportsbookdataservice.model.SportEvent;
import com.onseo.sportsbookdataservice.service.SportEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sport-events")
public class SportEventController {
    @Autowired
    private SportEventService service;

    @PostMapping
    public SportEvent createEvent(@RequestBody SportEvent event) {
        return service.createEvent(event);
    }

    @PutMapping
    public SportEvent updateEvent(@RequestBody SportEvent event) {
        return service.updateEvent(event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable String id) {
        service.deleteEvent(id);
    }
}
