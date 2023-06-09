package com.example.ticketbookingrailwayapplication.dao;

import com.example.ticketbookingrailwayapplication.model.Station;
import com.example.ticketbookingrailwayapplication.model.Train;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {

    @Transactional
    @Modifying
    @Query("SELECT s FROM Station s WHERE s.city = :#{#city}")
    List<Station> findByCity(@Param("city") String city);

    @Transactional
    @Modifying
    @Query("UPDATE Station s SET s.city = :#{#station.city}, s.time = :#{#station.time} WHERE s.id = :id")
    int updateById(@Param("station") Station station, @Param("id") int id);

    @Transactional
    @Modifying
    @Query("SELECT s FROM Station  s WHERE s.train= :#{#train} AND  s.city = :#{#city}")
    List<Station> findStationByNameAndTrain(@Param("city") String city, @Param("train") Train train);
}
