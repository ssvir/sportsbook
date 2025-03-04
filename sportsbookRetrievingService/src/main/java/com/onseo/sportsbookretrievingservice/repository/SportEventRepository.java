package com.onseo.sportsbookretrievingservice.repository;

import com.onseo.sportsbookretrievingservice.model.SportEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SportEventRepository extends MongoRepository<SportEvent, String> {
    boolean existsById(String id);
    Optional<SportEvent> findById(String id);
    @Query("{ 'markets.id': ?0 }")
    Optional<SportEvent> findByMarketId(String marketId);
    @Query(value = "{ 'sport': ?0, 'settled': false }", fields = "{ 'markets': 0 }", sort = "{ 'startTime': -1 }")
    List<SportEvent> findBySportAndSettledFalseOrderByStartTimeWithoutMarkets(String sport);
}
