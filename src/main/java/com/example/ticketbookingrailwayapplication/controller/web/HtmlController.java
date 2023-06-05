package com.example.ticketbookingrailwayapplication.controller.web;

import com.example.ticketbookingrailwayapplication.model.Station;
import com.example.ticketbookingrailwayapplication.model.Train;
import com.example.ticketbookingrailwayapplication.model.User;
import com.example.ticketbookingrailwayapplication.service.StationService;
import com.example.ticketbookingrailwayapplication.service.TrainService;
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

    @Autowired
    public HtmlController(StationService stationService, TrainService trainService) {
        this.stationService = stationService;
        this.trainService = trainService;
    }

    @GetMapping("/hello")
    public String hello(
            @AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user.getUsername());
        return "hello";
    }

    @GetMapping("/selectStation")
    public String selectStation(Model model) {
        List<Station> stationsList = stationService.getAll();
        Set<String> stations = new HashSet<>();
        String city;
        for (var st : stationsList) {
            city=st.getCity();
            if (stations.contains(city)) {
               continue;
            }
            stations.add(city);
        }
        model.addAttribute("stations", stations);

        return "selectStation";
    }

    @PostMapping ("/selectStation")
    public String SelectedStations(String city1, String city2,Model model) {
        Set<Train> trains = stationService.findTrainsByStations(city1,city2);
        model.addAttribute("trains", trains);
        Set<Integer> seats=new HashSet<>();
                for (int i = 0; i <50 ; i++) {
            seats.add(i);
        }
        model.addAttribute("seats", seats);
                return "selectTrain";
    }
    @PostMapping("/selectTrain/seat")
    public String selectSeat(@AuthenticationPrincipal User user, Integer seat, Model model){

        model.addAttribute("seat", seat);
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        return "ticket";
    }

    @GetMapping("/allTrains")
    public String allTrains(Model model) {
        List<Train> trains = trainService.getAll();
        model.addAttribute("trains", trains);
        return "allTrains";
    }
    @GetMapping("/pay")
    public String payTicket(){
        return "pay";
    }


}
