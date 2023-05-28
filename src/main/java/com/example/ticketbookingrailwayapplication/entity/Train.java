package com.example.ticketbookingrailwayapplication.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "trains")
@Data

public class Train {
    @Id
    @GeneratedValue
    private int id;
    private String number;
    private String trainName;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "train", cascade = CascadeType.ALL)
    private List<Station> stations;

}
