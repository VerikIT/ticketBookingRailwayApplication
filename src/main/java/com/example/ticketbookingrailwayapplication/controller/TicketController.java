package com.example.ticketbookingrailwayapplication.controller;

import com.example.ticketbookingrailwayapplication.model.Ticket;
import com.example.ticketbookingrailwayapplication.service.TicketService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;
    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    @GetMapping
    public List<Ticket> getAll() {
        return ticketService.getAll();
    }
    @GetMapping("/{id}")
    public Ticket getById(@PathVariable int id) {
        return ticketService.getById(id);
    }
    @PostMapping
    public Ticket addNew(@RequestBody Ticket ticket) {
        return ticketService.addNew(ticket);
    }
    @PostMapping("/{trainId}/{startId}/{finishId}")
    public Ticket addNewByTrainAndStations(@PathVariable int trainId,@PathVariable int startId,@PathVariable int finishId, @RequestBody Ticket ticket) {
        return ticketService.addNewByTrainAndStations(ticket,trainId,startId,finishId);
    }
    @Transactional
    @PatchMapping("/{id}")
    public String updateById(@PathVariable int id, @RequestBody Ticket ticket) {
        int line = ticketService.updateById(id, ticket);
        if (line == 1) {
            return "Updated ticket: " + id + " done!";
        } else {
            return "Updated ticket: " + id + " failed!";
        }
    }
    @Transactional
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable int id) {
        ticketService.deleteById(id);
        return "Deleted ticket: " + id + " done!";
    }

}
