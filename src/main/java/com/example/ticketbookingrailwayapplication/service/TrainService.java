package com.example.ticketbookingrailwayapplication.service;

import com.example.ticketbookingrailwayapplication.dao.TrainRepository;
import com.example.ticketbookingrailwayapplication.entity.Train;
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
        {
            Train train1 = new Train();
            train1.setNumber("105К");
            train1.setTrainName("Київ-Одеса");
            addNew(train1);
            Train train2 = new Train();
            train2.setNumber("022Л");
            train2.setTrainName("ЛЬвів-Харків");
            addNew(train2);
            Train train3 = new Train();
            train3.setNumber("038К");
            train3.setTrainName("Київ-Запоріжжя");
            addNew(train3);
        }

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
