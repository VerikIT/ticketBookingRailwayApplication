package com.example.ticketbookingrailwayapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tickets")
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "start_station_id", nullable = false)
    private Station startStation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "finish_station_id", nullable = false)
    private Station finishStation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    private String passFirstName;
    private String passLastName;
    private int seatNumber;
    private double price;

}
