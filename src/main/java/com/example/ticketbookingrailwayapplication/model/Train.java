package com.example.ticketbookingrailwayapplication.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "trains")
@Data

public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String number;
    private String trainName;
    private double fullPrice;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "train", cascade = CascadeType.ALL)
    private List<Station> stations;
    @ElementCollection(targetClass = Integer.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "seats", joinColumns = @JoinColumn(name = "train_id"))
    private Set<Integer> seats=new HashSet<>();


}
