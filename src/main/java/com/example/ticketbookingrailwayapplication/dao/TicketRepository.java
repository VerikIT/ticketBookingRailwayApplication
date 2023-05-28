package com.example.ticketbookingrailwayapplication.dao;

import com.example.ticketbookingrailwayapplication.entity.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Ticket t SET t.train = :#{#ticket.train}, t.startStation = :#{#ticket.startStation}, t.finishStation = :#{#ticket.finishStation}, " +
            "t.passFirstName = :#{#ticket.passFirstName}, t.passLastName = :#{#ticket.passLastName}, t.seatNumber = :#{#ticket.seatNumber}, " +
            "t.price = :#{#ticket.price} WHERE t.id = :id")
    int updateById(@Param("ticket") Ticket ticket, @Param("id") int id);
    //    @Query("UPDATE Ticket t SET t.train = :train, t.startStation = :startStation, t.finishStation = :finishStation, " +
//            "t.passFirstName = :passFirstName, t.passLastName = :passLastName, t.seatNumber = :seatNumber, " +
//            "t.price = :price WHERE t.id = :id")
//    int updateById(@Param("train") Train train, @Param("startStation") Station startStation,
//                   @Param("finishStation") Station finishStation, @Param("passFirstName") String passFirstName,
//                   @Param("passLastName") String passLastName, @Param("seatNumber") Integer seatNumber,
//                   @Param("price") double price, @Param("id") int id);

}

