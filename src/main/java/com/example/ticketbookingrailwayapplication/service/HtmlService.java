package com.example.ticketbookingrailwayapplication.service;


import com.example.ticketbookingrailwayapplication.model.Station;
import com.example.ticketbookingrailwayapplication.model.Ticket;
import com.example.ticketbookingrailwayapplication.model.Train;
import com.example.ticketbookingrailwayapplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class HtmlService {

    private final StationService stationService;
    private final TrainService trainService;
    private final UserService userService;
    private final TicketService ticketService;

    @Autowired
    public HtmlService(
            StationService stationService,
            TrainService trainService,
            UserService userService,
            TicketService ticketService
    ) {
        this.stationService = stationService;
        this.trainService = trainService;
        this.userService = userService;
        this.ticketService = ticketService;
    }

    @Transactional
    public Set<Train> allTrains() {
        Set<Train> trains = new HashSet<>(trainService.getAll());
        addSeats(trains);
        return trains;
    }

    public List<Ticket> paidTickets(User user) {

        return ticketService.findTicketsByUser(user);
    }

    @Transactional(readOnly = true)
    public List<String> selectStation() {
        List<Station> stationsList = stationService.getAll();
        List<String> stations = new ArrayList<>();
        String city;
        for (var st : stationsList) {
            city = st.getCity();
            if (stations.contains(city)) {
                continue;
            }
            stations.add(city);
        }
        Collections.sort(stations);

        return stations;
    }

    @Transactional
    public Set<Train> selectedStations
            (String start,
             String finish) {

        Set<Train> trains = stationService.findTrainsByStations(start, finish);
        addSeats(trains);

        return trains;
    }

    @Transactional
    public Ticket addPassData(User user,
                              LocalDate date,
                              Integer seat,
                              String start,
                              String finish,
                              Integer trainId) {
        Ticket ticket = new Ticket();
        ticket.setDate(date);
        User user1 = userService.getUserById(user.getId());
        ticket.setUser(user1);
        ticket.setPassFirstName(user1.getFirstName());
        ticket.setPassLastName(user1.getLastName());

        Train train = trainService.getById(trainId);
        ticket.setTrain(train);

        Station startStation = stationService.findStationByNameAndTrain(start, train);
        Station finishStation = stationService.findStationByNameAndTrain(finish, train);

        ticket.setStartStation(startStation);
        ticket.setFinishStation(finishStation);
        ticket.setPrice(trainService.priceTrip(train, startStation, finishStation));

        ticket.setSeatNumber(seat);
        ticket.setPaid(false);

        ticketService.addNew(ticket);
        return ticket;
    }

    @Transactional
    public void passengerData
            (
                    Integer ticketId,
                    LocalDate date,
                    Integer seat,
                    String firstName,
                    String lastName
            ) {
        Ticket ticket = ticketService.getById(ticketId);
        ticket.getTrain().getSeats().remove(seat);
        if (ticket.getSeatNumber() != seat) {
            ticket.getTrain().getSeats().add(ticket.getSeatNumber());
            ticket.setSeatNumber(seat);
            ticket.getTrain().getSeats().remove(seat);
        }
        ticket.setPassFirstName(firstName);
        ticket.setPassLastName(lastName);
        ticket.setDate(date);
        ticketService.updateById(ticket, ticketId);

    }

    public Ticket updatePassData(Integer ticketId) {
        return ticketService.getById(ticketId);
    }


    public void deleteTicket(Integer ticketId) {
        ticketService.deleteById(ticketId);
    }


@Transactional
public List<Ticket> payTickets(Integer[] selectedTickets) {
    List<Ticket> tickets = new ArrayList<>();
    for (Integer ticketId : selectedTickets
    ) {
        Ticket ticket = ticketService.getById(ticketId);
        ticket.setPaid(true);
        tickets.add(ticket);
    }
    return tickets;
}



    private void addSeats(Set<Train> trains) {
        Set<Integer> newSeats = new HashSet<>();
        for (int i = 1; i <= 50; i++) {
            newSeats.add(i);
        }
        for (Train train : trains
        ) {
            if (train.getSeats().isEmpty()) {
                train.setSeats(newSeats);
                trainService.updateById(train.getId(), train);
            }
        }
    }


}