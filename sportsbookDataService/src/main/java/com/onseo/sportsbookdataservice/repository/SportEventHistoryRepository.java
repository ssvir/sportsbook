package com.onseo.sportsbookdataservice.repository;

import com.onseo.sportsbookdataservice.model.SportEvent;
import com.onseo.sportsbookdataservice.model.SportEventHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SportEventHistoryRepository extends MongoRepository<SportEventHistory, String> {
    Boolean existsByIdOrSportId(String id, String sportId);
    void deleteBySportId(String sportId);
    SportEvent findSportEventBySportId(String sportId);

    List<SportEvent> findBySportAndSettledFalseOrderByStartTime(String sport);
    @Query("{ 'markets.id': ?0 }")
    Optional<SportEvent> findByMarketId(String marketId);
    @Query(value = "{ 'sport': ?0, 'settled': false }", fields = "{ 'markets': 0 }", sort = "{ 'startTime': -1 }")
    List<SportEvent> findEventWithoutMarkets(String sport);
}