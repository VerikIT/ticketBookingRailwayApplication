package com.example.ticketbookingrailwayapplication.controller.web;

import com.example.ticketbookingrailwayapplication.model.*;
import com.example.ticketbookingrailwayapplication.service.StationService;
import com.example.ticketbookingrailwayapplication.service.TicketService;
import com.example.ticketbookingrailwayapplication.service.TrainService;
import com.example.ticketbookingrailwayapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.*;

@Controller

public class HtmlController {

    private final StationService stationService;
    private final TrainService trainService;
    private final UserService userService;
    private final TicketService ticketService;


    @Autowired
    public HtmlController(
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

    @GetMapping("/hello")
    public String hello
            (
                    @AuthenticationPrincipal User user,
                    Model model
            ) {
        model.addAttribute("user", user.getUsername());
        return "hello";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/allTrains")
    public String allTrains(Model model) {
        Set<Train> trains = new HashSet<>(trainService.getAll());
       addSeats(trains);
        model.addAttribute("trains", trains);
        return "allTrains";
    }
    private void addSeats(Set<Train> trains){
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

    @GetMapping("/paidTickets")
    public String paidTickets(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        List<Ticket> tickets = ticketService.findTicketsByUser(user);
        model.addAttribute("tickets", tickets);
        return "paidTickets";
    }

    @GetMapping("/selectStation")
    public String selectStation
            (
                    @AuthenticationPrincipal User user,
                    boolean equalsSt,
                    Model model
            ) {
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
        model.addAttribute("equalsSt", equalsSt);
        model.addAttribute("stations", stations);
        boolean isAdmin = user.getRoles().contains(Role.ROLE_ADMIN);
        model.addAttribute("isAdmin", isAdmin);
        return "selectStation";
    }


    @PostMapping("/selectTrain")
    public String SelectedStations
            (
                    String start,
                    String finish,
                    LocalDate date,
                    Model model
            ) {
        if (start.equals(finish)) {
            return "redirect:/selectStation?equalsSt=true";
        }
        Set<Train> trains = stationService.findTrainsByStations(start, finish);
        addSeats(trains);
        model.addAttribute("trains", trains);
        model.addAttribute("start", start);
        model.addAttribute("date", date);
        model.addAttribute("finish", finish);
        model.addAttribute("finish", finish);
        return "selectTrain";
    }

    @GetMapping("/passData")
    public String addDPassData
            (
                    @AuthenticationPrincipal User user,
                    LocalDate date,
                    Integer seat,
                    String start,
                    String finish,
                    Integer trainId,
                    Model model
            ) {
        Ticket ticket = new Ticket();
        model.addAttribute("ticket", ticket);
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

        ticketService.addNew(ticket);
        return "passData";
    }

    @PostMapping("/passData")
    public String passengerData
            (
                    Integer ticketId,
                    LocalDate date,
                    Integer seat,
                    String firstName,
                    String lastName, Model model
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
        model.addAttribute("date", date);
        model.addAttribute("seat", seat);
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        return "redirect:/paidTickets";
    }

    @GetMapping("/updPassData")
    public String updatePassData(Integer ticketId, Model model) {
        Ticket ticket = ticketService.getById(ticketId);
        model.addAttribute("ticket", ticket);

        return "passData";
    }

    @GetMapping("/deleteTicket")
    public String deleteTicket(Integer ticketId) {
        ticketService.deleteById(ticketId);
        return "redirect:/paidTickets";
    }

    @GetMapping("/pay")
    public String payTicket() {
        return "pay";
    }
}
