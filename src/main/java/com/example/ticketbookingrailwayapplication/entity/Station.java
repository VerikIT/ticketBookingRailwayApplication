package com.example.ticketbookingrailwayapplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "stations")
@Data
//@ToString
//@EqualsAndHashCode
//@Getter
//
//@RequiredArgsConstructor

public class Station {
    @Id
    @GeneratedValue
    private int id;
    private String city;
    @Transient
    private int trainId;
    private LocalTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id", nullable = false)
    @JsonIgnore
    private Train train;
      public void setTrainId() {
        this.trainId =  this.train.getId();
    }
}
