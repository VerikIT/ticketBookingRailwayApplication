package com.example.ticketbookingrailwayapplication.service;

import com.example.ticketbookingrailwayapplication.dao.TrainRepository;
import com.example.ticketbookingrailwayapplication.model.Station;
import com.example.ticketbookingrailwayapplication.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Transactional(readOnly = true)
    public Train getById(int id) {
        return trainRepository.findById(id).orElse(null);
    }
    @Transactional(readOnly = true)
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

    public int updateById(int id, Train train) {
        return trainRepository.updateById(train, id);
    }


}
