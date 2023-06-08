package com.example.ticketbookingrailwayapplication.controller.web;

import com.example.ticketbookingrailwayapplication.model.Station;
import com.example.ticketbookingrailwayapplication.model.Ticket;
import com.example.ticketbookingrailwayapplication.model.Train;
import com.example.ticketbookingrailwayapplication.model.User;
import com.example.ticketbookingrailwayapplication.service.StationService;
import com.example.ticketbookingrailwayapplication.service.TicketService;
import com.example.ticketbookingrailwayapplication.service.TrainService;
import com.example.ticketbookingrailwayapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

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

    @GetMapping("/allTrains")
    public String allTrains(Model model) {
        List<Train> trains = trainService.getAll();
        model.addAttribute("trains", trains);
        return "allTrains";
    }

    @GetMapping("/selectStation")
    public String selectStation(Model model) {
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

        return "selectStation";
    }

    @PostMapping("/selectTrain")
    public String SelectedStations(String city1, String city2, Model model) {
        Set<Train> trains = stationService.findTrainsByStations(city1, city2);
        Set<Integer> seats = new HashSet<>();
        for (int i = 0; i < 99; i++) {
            seats.add(i);
        }
        Ticket ticket = new Ticket();
        model.addAttribute("trains", trains);
        model.addAttribute("seats", seats);
        model.addAttribute("start", city1);
        model.addAttribute("finish", city2);
        return "selectTrain";
    }

    @PostMapping("/ticket")
    public String selectSeat(@AuthenticationPrincipal User user, Integer seat, String start, String finish, Integer trainId, Model model) {
        Ticket ticket = new Ticket();
        Train train = trainService.getById(trainId);
        ticket.setTrain(train);

        ticket.setStartStation(stationService.findStationByNameAndTrain(start,train));
        ticket.setFinishStation(stationService.findStationByNameAndTrain(finish,train));

        User userFromDb = userService.findByUsername(user.getUsername());
        ticket.setPassFirstName(userFromDb.getFirstName());
        ticket.setPassLastName(userFromDb.getLastName());

        ticket.setSeatNumber(seat);
        ticketService.addNew(ticket);



        model.addAttribute("trainNumber", ticket.getTrain().getNumber());
        model.addAttribute("trainName", ticket.getTrain().getTrainName());
        model.addAttribute("start", ticket.getStartStation().getCity());
        model.addAttribute("finish", ticket.getFinishStation().getCity());
        model.addAttribute("seat", ticket.getSeatNumber());
        model.addAttribute("firstName", ticket.getPassFirstName());
        model.addAttribute("lastName", ticket.getPassLastName());

        return "ticket";
    }


    @GetMapping("/pay")
    public String payTicket() {
        return "pay";
    }


}
