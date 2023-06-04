package com.example.ticketbookingrailwayapplication.service;

import com.example.ticketbookingrailwayapplication.dao.StationRepository;
import com.example.ticketbookingrailwayapplication.model.Station;
import com.example.ticketbookingrailwayapplication.model.Train;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {
    private final StationRepository stationRepository;
    private final TrainService trainService;

    @Autowired
    public StationService(StationRepository stationRepository, TrainService trainService) {
        this.stationRepository = stationRepository;
        this.trainService = trainService;
    }


    public Station addNew(Station station) {
        return stationRepository.save(station);
    }

    @Transactional
    public Station addNewByTrain(Station station, int id) {
        Train train = trainService.getById(id);
        station.setTrain(train);
        return stationRepository.save(station);
    }

    @Transactional
    public Station getById(int id) {
        Station station = stationRepository.findById(id).orElse(null);
//        station.setTrainId();
        return station;
    }

    @Transactional
    public List<Station> getAll() {
        List<Station> stations = stationRepository.findAll();
//        for (Station st:stations
//             ) {
//            st.setTrainId();
//        }
        return stations;
    }

    @Transactional
    public int updateById(int id, Station station) {
        return stationRepository.updateById(station, id);
    }

    public void deleteById(int id) {
        stationRepository.deleteById(id);
    }


}