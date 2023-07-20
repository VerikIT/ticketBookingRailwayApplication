package com.example.ticketbookingrailwayapplication.controller.web;

import com.example.ticketbookingrailwayapplication.model.Role;
import com.example.ticketbookingrailwayapplication.model.Ticket;
import com.example.ticketbookingrailwayapplication.model.Train;
import com.example.ticketbookingrailwayapplication.model.User;
import com.example.ticketbookingrailwayapplication.service.HtmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Controller

public class HtmlController {
    private final HtmlService htmlService;

    @Autowired
    public HtmlController(
            HtmlService htmlService) {
        this.htmlService = htmlService;
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
        Set<Train> trains = htmlService.allTrains();
        model.addAttribute("trains", trains);
        return "allTrains";
    }


    @GetMapping("/paidTickets")
    public String paidTickets(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        List<Ticket> tickets = htmlService.paidTickets(user);
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
        List<String> stations = htmlService.selectStation();
        model.addAttribute("equalsSt", equalsSt);
        model.addAttribute("stations", stations);
        boolean isAdmin = user.getRoles().contains(Role.ROLE_ADMIN);
        model.addAttribute("isAdmin", isAdmin);
        return "selectStation";
    }


    @PostMapping("/selectTrain")
    public String selectedStations
            (
                    String start,
                    String finish,
                    LocalDate date,
                    Model model
            ) {
        if (start.equals(finish)) {
            return "redirect:/selectStation?equalsSt=true";
        }
        Set<Train> trains = htmlService.selectedStations(start, finish);

        model.addAttribute("trains", trains);
        model.addAttribute("start", start);
        model.addAttribute("date", date);
        model.addAttribute("finish", finish);
        model.addAttribute("finish", finish);
        return "selectTrain";
    }

    @GetMapping("/passData")
    public String addPassData
            (
                    @AuthenticationPrincipal User user,
                    LocalDate date,
                    Integer seat,
                    String start,
                    String finish,
                    Integer trainId,
                    Model model
            ) {
        Ticket ticket = htmlService.addPassData(user, date, seat, start, finish, trainId);
        model.addAttribute("ticket", ticket);
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
        htmlService.passengerData(ticketId, date, seat, firstName, lastName);
        model.addAttribute("date", date);
        model.addAttribute("seat", seat);
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        return "redirect:/paidTickets";
    }

    @GetMapping("/updPassData")
    public String updatePassData(Integer ticketId, Model model) {
        Ticket ticket = htmlService.updatePassData(ticketId);
        model.addAttribute("ticket", ticket);
        return "passData";
    }

    @GetMapping("/deleteTicket")
    public String deleteTicket(Integer ticketId) {
        htmlService.deleteTicket(ticketId);
        return "redirect:/paidTickets";
    }

    @PostMapping ("/pay")
    public String payTickets(Integer[] selectedTickets, Model model) {
        List<Ticket> tickets= htmlService.payTickets(selectedTickets);

        double sum=0;
        for (Ticket ticket:tickets
             ) {
           sum +=ticket.getPrice();
        }
        model.addAttribute("size", tickets.size());
        model.addAttribute("sum", sum);
        return "pay";
    }
}
