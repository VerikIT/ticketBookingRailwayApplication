package com.example.ticketbookingrailwayapplication.controller;

import com.example.ticketbookingrailwayapplication.model.Station;
import com.example.ticketbookingrailwayapplication.service.StationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationController {
    private final StationService stationService;
    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }
    @GetMapping
    public List<Station> getAll() {
        return stationService.getAll();
    }

    @GetMapping("/{id}")
    public Station getById(@PathVariable int id) {
        return stationService.getById(id);
    }

    @PostMapping
    public Station addNew(@RequestBody Station station) {
        return stationService.addNew(station);
    }
    @PostMapping("/{id}/train")
    public Station addNewByTrain(@PathVariable int id, @RequestBody Station station) {
        return stationService.addNewByTrain(station,id);
    }

    @Transactional
    @PatchMapping("/{id}")
    public String updateById(@PathVariable int id, @RequestBody Station station) {
        int line = stationService.updateById(id, station);
        if (line == 1) {
            return "Updated station: " + id + " done!";
        } else {
            return "Updated station: " + id + " failed!";
        }
    }

    @Transactional
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable int id) {
        stationService.deleteById(id);
        return "Deleted station: " + id + " done!";
    }

}
