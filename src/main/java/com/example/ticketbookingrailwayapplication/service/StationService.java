package com.example.ticketbookingrailwayapplication.service;

import com.example.ticketbookingrailwayapplication.dao.StationRepository;
import com.example.ticketbookingrailwayapplication.model.Station;
import com.example.ticketbookingrailwayapplication.model.Train;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public Set<Train> findTrainsByStations(String startCity, String finishCity) {
        List<Station> startSt = findStationsByCity(startCity);
        List<Station> finishSt = findStationsByCity(finishCity);
        Set<Train> trains = new HashSet<>();
        for (Station st1 : startSt) {
            for (Station st2 : finishSt) {
                if (st1.getTrain().equals(st2.getTrain())) {
                    trains.add(st1.getTrain());
                }
            }
        }
        return trains;
    }

    public List<Station> findStationsByCity(String city) {
        return stationRepository.findByCity(city);
    }
    @Transactional
    public Station findStationByNameAndTrain(String city, Train train) {
        List<Station> stations= stationRepository.findStationByNameAndTrain(city,train);
        if (!stations.isEmpty()) {
            return stations.get(0);
        }
        return null;
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
        return station;
    }

    @Transactional
    public List<Station> getAll() {
        List<Station> stations = stationRepository.findAll();
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
