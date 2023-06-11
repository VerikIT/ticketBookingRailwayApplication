package com.example.ticketbookingrailwayapplication.dao;

import com.example.ticketbookingrailwayapplication.model.Ticket;
import com.example.ticketbookingrailwayapplication.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Ticket t SET t.train = :#{#ticket.train}, t.startStation = :#{#ticket.startStation}, t.finishStation = :#{#ticket.finishStation}, " +
            "t.passFirstName = :#{#ticket.passFirstName}, t.passLastName = :#{#ticket.passLastName}, t.seatNumber = :#{#ticket.seatNumber}, " +
            "t.price = :#{#ticket.price} WHERE t.id = :id")
    int updateById(@Param("ticket") Ticket ticket, @Param("id") int id);

    List<Ticket> findTicketsByUser(User user);

}

