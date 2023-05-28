package com.example.ticketbookingrailwayapplication.service;

import com.example.ticketbookingrailwayapplication.dao.StationRepository;
import com.example.ticketbookingrailwayapplication.entity.Station;
import com.example.ticketbookingrailwayapplication.entity.Train;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class StationService {
    private final StationRepository stationRepository;
    private final TrainService trainService;

    @Autowired
    public StationService(StationRepository stationRepository, TrainService trainService) {
        this.stationRepository = stationRepository;
        this.trainService = trainService;
        { //1,105К,Київ-Одеса
            Station station = hardAddStation("Київ", 1, 21, 17);
            addNew(station);
            station = hardAddStation("Вінниця", 1, 0, 15);
            addNew(station);
            station = hardAddStation("Одеса", 1, 6, 15);
            addNew(station);
            //2,022Л,ЛЬвів-Харків
            station = hardAddStation("Львів", 2, 18, 35);
            addNew(station);
            station = hardAddStation("Тернопіль", 2, 20, 30);
            addNew(station);
            station = hardAddStation("Хмельницький", 2, 22, 20);
            addNew(station);
            station = hardAddStation("Київ", 2, 3, 2);
            addNew(station);
            station = hardAddStation("Полтава", 2, 7, 37);
            addNew(station);
            station = hardAddStation("Харків", 2, 10, 18);
            addNew(station);
            //3,038К,Київ-Запоріжжя
            station = hardAddStation("Київ", 3, 21, 18);
            addNew(station);
            station = hardAddStation("Дніпро", 3, 5, 8);
            addNew(station);
            station = hardAddStation("Запоріжжя", 3, 7, 38);
            addNew(station);
        }
    }

    @Transactional
    private Station hardAddStation(String city, int trainId, int hour, int minute) {
        Station station = new Station();
        station.setCity(city);
        station.setTrain(trainService.getById(trainId));
        LocalTime time = LocalTime.of(hour, minute);
        station.setTime(time);

        return station;
    }


    public Station addNew(Station station) {
        return stationRepository.save(station);
    }
    @Transactional
    public Station addNewByTrain(Station station, int id) {
        Train train=trainService.getById(id);
        station.setTrain(train);
        return stationRepository.save(station);
    }
    @Transactional
    public Station getById(int id){
        Station station =stationRepository.findById(id).orElse(null);
        station.setTrainId();
return station;
           }
    @Transactional
    public List<Station> getAll() {
        List<Station> stations=stationRepository.findAll();
        for (Station st:stations
             ) {
            st.setTrainId();
        }
        return stations;
    }

    @Transactional
    public int updateById(int id, Station station) {
        return  stationRepository.updateById(station, id);
    }
    public void deleteById(int id) {
        stationRepository.deleteById(id);
    }


}
