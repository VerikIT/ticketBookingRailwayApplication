package com.example.ticketbookingrailwayapplication.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

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
    private User user;

    private String passFirstName;
    private String passLastName;
    private int seatNumber;
    private LocalDate date;
    private double price;
    private boolean paid;

    public String getStatus(){
        String status;
        if (this.isPaid()){
            status="придбано";
        }else {
            status="резерв";
        }
        return status;
    }

}
