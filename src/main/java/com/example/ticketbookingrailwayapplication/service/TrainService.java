package com.example.ticketbookingrailwayapplication.service;

import com.example.ticketbookingrailwayapplication.dao.TrainRepository;
import com.example.ticketbookingrailwayapplication.model.Station;
import com.example.ticketbookingrailwayapplication.model.Train;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service

public class TrainService {
    private final TrainRepository trainRepository;

    @Autowired
    public TrainService(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;


    }


    public Train addNew(Train train) {
        return trainRepository.save(train);
    }

    public Train getById(int id) {
        return trainRepository.findById(id).orElse(null);
    }

    public List<Train> getAll() {
        return trainRepository.findAll();
    }

    public void deleteById(int id) {
        trainRepository.deleteById(id);
    }

    public double priceTrip(Train train, Station start, Station finish) {
        double price = train.getFullPrice() / (train.getStations().size() - 1);
        int quantity = train.getStations().indexOf(finish)
                - train.getStations().indexOf(start);
        if (quantity != 0) {
            price = price*quantity;
        }else{
            price =0;
        }
        price = Math.round(price * 100.0) / 100.0;
        return price;
    }

    @Transactional
    public int updateById(int id, Train train) {
        return trainRepository.updateById(train, id);
    }


}
