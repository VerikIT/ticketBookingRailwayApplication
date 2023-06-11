package com.example.ticketbookingrailwayapplication.service;

import com.example.ticketbookingrailwayapplication.dao.TicketRepository;
import com.example.ticketbookingrailwayapplication.model.Station;
import com.example.ticketbookingrailwayapplication.model.Ticket;
import com.example.ticketbookingrailwayapplication.model.Train;
import com.example.ticketbookingrailwayapplication.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TrainService trainService;
    private final StationService stationService;
    private final UserService userService;

    @Autowired
    public TicketService(TicketRepository ticketRepository, TrainService trainService, StationService stationService, UserService userService) {
        this.ticketRepository = ticketRepository;
        this.trainService = trainService;
        this.stationService = stationService;
        this.userService = userService;
    }

    public Ticket addNew(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket addNewByTrainStationsUser(Ticket ticket, int trainId, int startId, int finishId, int userId) {
        Train train = trainService.getById(trainId);
        ticket.setTrain(train);
        Station startStation = stationService.getById(startId);
        ticket.setStartStation(startStation);
        Station finishStation = stationService.getById(finishId);
        ticket.setFinishStation(finishStation);
        User user=userService.getUserById(userId);
        ticket.setUser(user);
        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket getById(int id) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        return ticket;
    }

    @Transactional
    public List<Ticket> getAll() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets;
    }

    @Transactional
    public int updateById(Ticket ticket,int id) {
        return ticketRepository.updateById(ticket, id);
    }

    public void deleteById(int id) {
        ticketRepository.deleteById(id);
    }

    public List<Ticket> findTicketsByUser(User user) {
        List<Ticket> tickets = ticketRepository.findTicketsByUser(user);
        return tickets;


    }

}
