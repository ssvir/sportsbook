package com.onseo.sportsbookdataservice.service;

import com.hazelcast.internal.util.StringUtil;
import com.hazelcast.map.IMap;
import com.onseo.sportsbookdataservice.model.SportEvent;
import com.onseo.sportsbookdataservice.model.SportEventHistory;
import com.onseo.sportsbookdataservice.repository.SportEventHistoryRepository;
import com.onseo.sportsbookdataservice.repository.SportEventRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SportEventServiceImpl implements SportEventService {
    @Autowired
    private SportEventRepository sportEventRepository;
    @Autowired
    private SportEventHistoryRepository sportEventHistoryRepository;
    @Autowired
    private IMap<String, Optional<SportEvent>> sportEventMap;
    @Autowired
    private IMap<String, List<SportEvent>> sportsEventMap;

    public SportEvent createEvent(SportEvent event) {
        if (event.getId() != null && sportEventRepository.existsById(event.getId())) {
            throw new IllegalArgumentException("SportEvent already exists");
        }
        generateIdForNestedObjects(event);

        event = sportEventRepository.save(event);
        updateCache(event, Operation.UPDATE);

        return event;
    }

    public SportEvent updateEvent(SportEvent event) {
        Optional.of(event)
                .filter(sportEvent -> !StringUtil.isNullOrEmpty(sportEvent.getId()))
                .orElseThrow(() -> new IllegalArgumentException("id can not be null"));

        generateIdForNestedObjects(event);
        SportEventHistory sportEventHistory = new SportEventHistory();
        BeanUtils.copyProperties(event, sportEventHistory);

        sportEventHistory.setId(null);
        sportEventHistory.setSportId(event.getId());
        sportEventHistory.setStartTime(event.getStartTime());

        event = sportEventRepository.save(event);
        sportEventHistoryRepository.save(sportEventHistory);
        updateCache(event, Operation.UPDATE);

        return event;
    }

    public void deleteEvent(String id) {
        SportEvent sportEvent = sportEventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SportEvent not found"));
        sportEventRepository.delete(sportEvent);
        sportEventHistoryRepository.deleteBySportId(sportEvent.getId());
        updateCache(sportEvent, Operation.DELETE);
    }

    private void generateIdForNestedObjects(SportEvent event) {
        if (event.getMarkets() != null) {
            event.getMarkets().forEach(market -> {
                if (StringUtil.isNullOrEmpty(market.getId())) {
                    market.setId(UUID.randomUUID().toString());
                }
                market.getOutcomes().forEach(outcome -> {
                    if (StringUtil.isNullOrEmpty(outcome.getId())) {
                        outcome.setId(UUID.randomUUID().toString());
                    }
                });
            });
        }
    }

    private void updateCache(SportEvent event, Operation operation) {
        if (operation == Operation.UPDATE) {
            sportEventMap.put(event.getId(), Optional.of(event));
            List<SportEvent> sportEvents = sportsEventMap.get(event.getId());
            if (sportEvents != null) {
                sportEvents.removeIf(sport -> sport.getId().equals(event.getId()));
                sportEvents.add(event);
            }
        } else if (operation == Operation.DELETE) {
            sportEventMap.remove(event.getId());
            List<SportEvent> sportEvents = sportsEventMap.get(event.getSport());
            if (sportEvents != null) {
                sportEvents.removeIf(sport -> sport.getId().equals(event.getId()));
            }
        }
    }

    enum Operation {
        UPDATE, DELETE
    }
}
