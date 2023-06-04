package com.example.ticketbookingrailwayapplication.service;

import com.example.ticketbookingrailwayapplication.dao.TrainRepository;
import com.example.ticketbookingrailwayapplication.model.Train;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Train getById(int id) {
        return trainRepository.findById(id).orElse(null);
    }

    public List<Train> getAll() {
        return trainRepository.findAll();
    }

    public void deleteById(int id) {
        trainRepository.deleteById(id);
    }

    @Transactional
    public int updateById(int id, Train train) {
//           return  trainRepository.updateById(train.getNumber(), train.getTrainName(), id);
                   return  trainRepository.updateById(train, id);
    }
}
