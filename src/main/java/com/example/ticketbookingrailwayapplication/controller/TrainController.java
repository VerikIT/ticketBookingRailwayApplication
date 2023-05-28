package com.example.ticketbookingrailwayapplication.controller;

import com.example.ticketbookingrailwayapplication.entity.Train;
import com.example.ticketbookingrailwayapplication.service.TrainService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trains")

public class TrainController {
    private final TrainService trainService;

    @Autowired
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping
    public List<Train> getAll() {
        return trainService.getAll();
    }

    @GetMapping("/{id}")
    public Train getById(@PathVariable int id) {
        return trainService.getById(id);
    }

    @PostMapping
    public Train addTrain(@RequestBody Train train) {
        return trainService.addNew(train);
    }

    @Transactional
    @PatchMapping("/{id}")
    public String updateById(@PathVariable int id, @RequestBody Train train) {
        int line = trainService.updateById(id, train);
        if (line == 1) {
            return "Updated train: " + id + " done!";
        } else {
            return "Updated train: " + id + " failed!";
        }
    }

    @Transactional
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable int id) {
        trainService.deleteById(id);
        return "Deleted train: " + id + " done!";
    }

}
