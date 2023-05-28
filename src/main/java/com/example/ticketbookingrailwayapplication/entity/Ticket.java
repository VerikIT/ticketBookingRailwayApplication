package com.example.ticketbookingrailwayapplication.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tickets")
@Data
public class Ticket {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;
//    @Transient
//    private String trainName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "start_station_id", nullable = false)
    private Station startStation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "finish_station_id", nullable = false)
    private Station finishStation;

    private String passFirstName;
    private String passLastName;
    private int seatNumber;
    private double price;

}
