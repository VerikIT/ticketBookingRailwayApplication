package com.example.ticketbookingrailwayapplication.dao;

import com.example.ticketbookingrailwayapplication.entity.Station;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station,Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Station s SET s.city = :#{#station.city}, s.time = :#{#station.time} WHERE s.id = :id")
    int updateById(@Param("station") Station station, @Param("id") int id);
}
