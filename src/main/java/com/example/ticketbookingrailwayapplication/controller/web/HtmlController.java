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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller

public class HtmlController {

    private final StationService stationService;
    private final TrainService trainService;
    private final UserService userService;
    private final TicketService ticketService;

    @Autowired
    public HtmlController(StationService stationService, TrainService trainService, UserService userService, TicketService ticketService) {
        this.stationService = stationService;
        this.trainService = trainService;
        this.userService = userService;
        this.ticketService = ticketService;
    }

    @GetMapping("/hello")
    public String hello(
            @AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user.getUsername());
        return "hello";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/allTrains")
    public String allTrains(Model model) {
        List<Train> trains = trainService.getAll();
        model.addAttribute("trains", trains);
        return "allTrains";
    }
    @GetMapping("/paidTickets")
    public String paidTickets(
            @AuthenticationPrincipal User user, Model model) {
        List<Ticket> tickets=ticketService.findTicketsByUser(user);
        model.addAttribute("tickets", tickets);
        return "tickets";
    }

    @GetMapping("/selectStation")
    public String selectStation(@AuthenticationPrincipal User user, Model model) {
        List<Station> stationsList = stationService.getAll();
        Set<String> stations = new HashSet<>();
        String city;
        for (var st : stationsList) {
            city = st.getCity();
            if (stations.contains(city)) {
                continue;
            }
            stations.add(city);
        }
        model.addAttribute("stations", stations);
        boolean isAdmin = user.getRoles().contains(Role.ROLE_ADMIN);
        model.addAttribute("isAdmin", isAdmin);
        return "selectStation";
    }

    @PostMapping("/selectTrain")
    public String SelectedStations(String start, String finish, Model model) {
        Set<Train> trains = stationService.findTrainsByStations(start, finish);
        Set<Integer> seats = new HashSet<>();
        for (int i = 0; i < 99; i++) {
            seats.add(i);
        }
        model.addAttribute("trains", trains);
        model.addAttribute("seats", seats);
        model.addAttribute("start", start);
        model.addAttribute("finish", finish);
        return "selectTrain";
    }
    @GetMapping("/passData")
    public String addDetail(
            @AuthenticationPrincipal User user
            , Integer seat, String start, String finish, Integer trainId
            , Model model){
        Ticket ticket = new Ticket();
        model.addAttribute("ticket", ticket);
        ticket.setUser(user);
        ticket.setPassFirstName(user.getFirstName());
        ticket.setPassLastName(user.getLastName());

        Train train = trainService.getById(trainId);
        ticket.setTrain(train);

        ticket.setStartStation(stationService.findStationByNameAndTrain(start, train));
        ticket.setFinishStation(stationService.findStationByNameAndTrain(finish, train));

        ticket.setSeatNumber(seat);

        ticketService.addNew(ticket);


        return "passData";

    }
    @PostMapping("/passData")
public String passengerData(Integer ticketId, Integer seat, String firstName, String lastName, Model model){
        Ticket ticket=ticketService.getById(ticketId);
        ticket.setSeatNumber(seat);
        ticket.setPassFirstName(firstName);
        ticket.setPassLastName(lastName);
        ticketService.updateById(ticket,ticketId);
        model.addAttribute("seat", seat);
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);



        return "redirect:/paidTickets";
    }





    @GetMapping("/pay")
    public String payTicket() {
        return "pay";
    }


}
