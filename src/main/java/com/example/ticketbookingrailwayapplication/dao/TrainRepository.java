package com.example.ticketbookingrailwayapplication.dao;

import com.example.ticketbookingrailwayapplication.entity.Train;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TrainRepository extends JpaRepository<Train,Integer> {
    @Transactional
    @Modifying
//    @Query("UPDATE Train t SET t.number = ?1, t.trainName = ?2 WHERE t.id = ?3")
//    int updateById(String number, String trainName, int id);
//}
//    @Query("UPDATE Train t SET t.number = :number, t.trainName = :trainName WHERE t.id = :id")
//    int updateById(@Param("number") String number, @Param("trainName") String trainName, @Param("id") int id);
    @Query("UPDATE Train t SET t.number = :#{#train.number}, t.trainName = :#{#train.trainName} WHERE t.id = :id")
    int updateById(@Param("train") Train train, @Param("id") int id);
}